package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.github.paolobd.intellijgamificationplugin.enums.DailyAchievement
import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.communication.Event
import com.github.paolobd.intellijgamificationplugin.communication.EventType
import com.github.paolobd.intellijgamificationplugin.userInterface.MyNotifier
import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
class AchievementService(val project: Project) {

    fun analyzeEvents(events: List<Event>) {
        val globalAchExp = mutableMapOf<Int, Int>()
        val projectAchExp = mutableMapOf<Int, Int>()
        val dailyAchExp = mutableMapOf<Int, Int>()
        val projectEvents = ProjectStatePersistence.getInstance(project).state.eventList

        for (event in events) {

            val found = projectEvents.contains(event)
            projectEvents.add(event)

            if (!found) {
                var projectAchId: Int
                var globalAchId: Int
                when (event.eventType) {
                    EventType.CLICK -> {
                        projectAchId = ProjectAchievement.NUM_CLICKS.achievement.id
                        globalAchId = GlobalAchievement.NUM_CLICKS.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                    }

                    EventType.NAVIGATION -> {
                        projectAchId = ProjectAchievement.NUM_SITES.achievement.id
                        globalAchId = GlobalAchievement.NUM_SITES.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                    }

                    EventType.LOCATOR -> {

                    }

                    EventType.LOCATOR_ID -> {

                    }

                    EventType.LOCATOR_CSS -> {

                    }

                    EventType.LOCATOR_XPATH -> {

                    }
                }
            }
        }

        projectAchExp.forEach {
            addExp(project, false, getProjectAchievement(it.key), it.value)
        }

        globalAchExp.forEach {
            addExp(null, false, getGlobalAchievement(it.key), it.value)
        }

        val dailyState = ApplicationStatePersistence.getInstance().state.dailyAchievement
        dailyAchExp.forEach {
            if (it.key == dailyState.state.id) {
                addExp(null, true, getDailyAchievement(it.key), it.value)
            }
        }

        UserInterface.achievementTab.substituteAchievementPane(null)
    }

    private fun insertOrUpdateExp(map: MutableMap<Int, Int>, key: Int) {
        map[key] = (map[key] ?: 0) + 1
    }

    private fun getProjectAchievement(id: Int): Achievement {
        return ProjectAchievement.values().map { it.achievement }.first { it.id == id }
    }

    private fun getGlobalAchievement(id: Int): Achievement {
        return GlobalAchievement.values().map { it.achievement }.first { it.id == id }
    }

    private fun getDailyAchievement(id: Int): Achievement {
        return DailyAchievement.values().map { it.achievement }.first { it.id == id }
    }

    private fun addExp(project: Project?, daily: Boolean, achievement: Achievement, exp: Int) {
        val achievementState = if (daily) {
            ApplicationStatePersistence.getInstance().state.dailyAchievement.state
        } else {
            if (project != null) {
                ProjectStatePersistence.getInstance(project).state.achievementList.first {
                    it.id == achievement.id
                }
            } else {
                ApplicationStatePersistence.getInstance().state.achievementList.first {
                    it.id == achievement.id
                }
            }
        }

        val oldExp = achievementState.currentExp

        //Achievement already completed
        if (oldExp >= achievement.milestone.last() || exp == 0) {
            return
        }

        //update the value in the state
        var index = 0
        var newExp = oldExp + exp

        while (index < achievement.milestone.size - 1 && oldExp >= achievement.milestone[index]) {
            index++
        }

        while (index < achievement.milestone.size - 1 && newExp >= achievement.milestone[index]) {
            MyNotifier.notifyAchievementMilestone(project, daily, achievement, index)
            ApplicationStatePersistence.getInstance().addUserExp(achievement.userExperience[index])
            index++
        }

        //only reached if we have more exp than required for the final milestone
        if (newExp >= achievement.milestone[index] && oldExp != achievement.milestone[index]) {
            newExp = achievement.milestone[index]
            MyNotifier.notifyAchievementMilestone(project, daily, achievement, index)
            ApplicationStatePersistence.getInstance().addUserExp(achievement.userExperience[index - 1])
        } else {
            val oldPer = oldExp.toFloat() / achievement.milestone[index] * 100
            val newPer = newExp.toFloat() / achievement.milestone[index] * 100
            val oldRounded = (oldPer / 25).toInt() * 25
            val newRounded = (newPer / 25).toInt() * 25

            if (newRounded > 0 && newRounded > oldRounded) {
                MyNotifier.notifyAchievementProgress(project, daily, achievement, index, newRounded)
            }
        }

        achievementState.currentExp = newExp

        //update the value in the UI
        if (daily) {
            UserInterface.userTab.dailyCard.updateProgress(achievementState.currentExp, index)
        } else {
            if (project != null) {
                UserInterface.achievementTab.updateProjectAchievement(
                    achievement.id,
                    achievementState.currentExp,
                    index
                )
            } else {
                UserInterface.achievementTab.updateGlobalAchievement(achievement.id, achievementState.currentExp, index)
            }
        }
    }

}