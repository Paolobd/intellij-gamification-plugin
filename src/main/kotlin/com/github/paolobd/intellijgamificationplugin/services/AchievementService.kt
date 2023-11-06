package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.github.paolobd.intellijgamificationplugin.enums.DailyAchievement
import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.communication.Event
import com.github.paolobd.intellijgamificationplugin.communication.EventType
import com.github.paolobd.intellijgamificationplugin.userInterface.MyNotifier
import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service
import com.intellij.openapi.project.Project

@Service
class AchievementService {

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
                var dailyAchId: Int
                when (event.eventType) {
                    EventType.NAVIGATION -> {
                        projectAchId = ProjectAchievement.NUM_NAVIGATION.achievement.id
                        globalAchId = GlobalAchievement.NUM_NAVIGATION.achievement.id
                        dailyAchId = DailyAchievement.DAILY_NAVIGATION.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)
                    }

                    EventType.NAVIGATION_BACK -> {
                        projectAchId = ProjectAchievement.NUM_NAVIGATION_BACK.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.NAVIGATION_FORWARD -> {
                        projectAchId = ProjectAchievement.NUM_NAVIGATION_FORWARD.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.NAVIGATION_REFRESH -> {
                        projectAchId = ProjectAchievement.NUM_NAVIGATION_REFRESH.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.LOCATOR -> {
                        projectAchId = ProjectAchievement.NUM_LOCATOR.achievement.id
                        globalAchId = GlobalAchievement.NUM_LOCATOR.achievement.id
                        dailyAchId = DailyAchievement.DAILY_LOCATOR.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)
                    }

                    EventType.LOCATOR_ID -> {
                        projectAchId = ProjectAchievement.NUM_LOCATOR.achievement.id
                        globalAchId = GlobalAchievement.NUM_LOCATOR.achievement.id
                        dailyAchId = DailyAchievement.DAILY_LOCATOR.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)

                        projectAchId = ProjectAchievement.NUM_LOCATOR_ID.achievement.id
                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.LOCATOR_NAME -> {
                        projectAchId = ProjectAchievement.NUM_LOCATOR.achievement.id
                        globalAchId = GlobalAchievement.NUM_LOCATOR.achievement.id
                        dailyAchId = DailyAchievement.DAILY_LOCATOR.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)

                        projectAchId = ProjectAchievement.NUM_LOCATOR_NAME.achievement.id
                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.LOCATOR_CSS -> {
                        projectAchId = ProjectAchievement.NUM_LOCATOR.achievement.id
                        globalAchId = GlobalAchievement.NUM_LOCATOR.achievement.id
                        dailyAchId = DailyAchievement.DAILY_LOCATOR.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)

                        projectAchId = ProjectAchievement.NUM_LOCATOR_CSS.achievement.id
                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.LOCATOR_XPATH -> {
                        projectAchId = ProjectAchievement.NUM_LOCATOR.achievement.id
                        globalAchId = GlobalAchievement.NUM_LOCATOR.achievement.id
                        dailyAchId = DailyAchievement.DAILY_LOCATOR.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)

                        projectAchId = ProjectAchievement.NUM_LOCATOR_XPATH.achievement.id
                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.ELEMENT_CLICK -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_CLICK.achievement.id
                        globalAchId = GlobalAchievement.NUM_ELEMENT_CLICK.achievement.id
                        dailyAchId = DailyAchievement.DAILY_CLICK.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)
                    }

                    EventType.ELEMENT_SEND_KEYS -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_SEND_KEYS.achievement.id
                        globalAchId = GlobalAchievement.NUM_ELEMENT_SEND_KEYS.achievement.id
                        dailyAchId = DailyAchievement.DAILY_SEND_KEYS.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)
                    }

                    EventType.ELEMENT_DISPLAYED -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_DISPLAYED.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.ELEMENT_SELECTED -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_SELECTED.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.ELEMENT_ENABLED -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_ENABLED.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.ELEMENT_TEXT -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_TEXT.achievement.id
                        globalAchId = GlobalAchievement.NUM_ELEMENT_TEXT.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                    }

                    EventType.ELEMENT_ATTRIBUTE -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_ATTRIBUTE.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.ELEMENT_TAG -> {

                    }

                    EventType.ELEMENT_CSS -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_CSS.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.LOGIN -> {
                        projectAchId = ProjectAchievement.NUM_LOGIN.achievement.id
                        globalAchId = GlobalAchievement.NUM_LOGIN.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                    }

                    EventType.SUBMIT -> {
                        projectAchId = ProjectAchievement.NUM_SUBMIT.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.TITLE -> {
                        projectAchId = ProjectAchievement.NUM_TITLE.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                    }

                    EventType.ALERT -> {
                        projectAchId = ProjectAchievement.NUM_ALERT.achievement.id
                        globalAchId = GlobalAchievement.NUM_ALERT.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                    }

                    EventType.ALERT_TEXT -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_TEXT.achievement.id
                        globalAchId = GlobalAchievement.NUM_ELEMENT_TEXT.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                    }

                    EventType.ALERT_SEND_KEYS -> {
                        projectAchId = ProjectAchievement.NUM_ELEMENT_SEND_KEYS.achievement.id
                        globalAchId = GlobalAchievement.NUM_ELEMENT_SEND_KEYS.achievement.id
                        dailyAchId = DailyAchievement.DAILY_SEND_KEYS.achievement.id

                        insertOrUpdateExp(projectAchExp, projectAchId)
                        insertOrUpdateExp(globalAchExp, globalAchId)
                        insertOrUpdateExp(dailyAchExp, dailyAchId)
                    }
                }
            }
        }

        projectAchExp.forEach {
            addExp(global = false, daily = false, getProjectAchievement(it.key), it.value)
        }

        globalAchExp.forEach {
            addExp(global = true, daily = false, getGlobalAchievement(it.key), it.value)
        }

        val dailyState = ApplicationStatePersistence.getInstance().state.dailyAchievement
        dailyAchExp.forEach {
            if (it.key == dailyState.state.id) {
                addExp(global = true, daily = true, getDailyAchievement(it.key), it.value)
            }
        }

        UserInterface.userTab.updateShowcaseProgress()
        //UserInterface.achievementTab.substituteAchievementPane(null)
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

    fun addExp(global: Boolean, daily: Boolean, achievement: Achievement, exp: Int) {
        val achievementState = if (daily) {
            ApplicationStatePersistence.getInstance().state.dailyAchievement.state
        } else {
            if (!global) {
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
            MyNotifier.notifyAchievementMilestone(project, global, daily, achievement, index)
            ApplicationStatePersistence.getInstance().addUserExp(achievement.userExperience[index])
            index++
        }

        //only reached if we have more exp than required for the final milestone
        if (newExp >= achievement.milestone[index] && oldExp != achievement.milestone[index]) {
            newExp = achievement.milestone[index]
            MyNotifier.notifyAchievementMilestone(project, global, daily, achievement, index)

            val userIndex = if (index == 0) {
                0
            } else {
                index - 1
            }
            ApplicationStatePersistence.getInstance().addUserExp(achievement.userExperience[userIndex])
        } /*else {
            val oldPer = oldExp.toFloat() / achievement.milestone[index] * 100
            val newPer = newExp.toFloat() / achievement.milestone[index] * 100
            val oldRounded = (oldPer / 25).toInt() * 25
            val newRounded = (newPer / 25).toInt() * 25

            if (newRounded > 0 && newRounded > oldRounded) {
                MyNotifier.notifyAchievementProgress(project, global, daily, achievement, index, newRounded)
            }
        }*/

        achievementState.currentExp = newExp

        //update the value in the UI
        if (daily) {
            UserInterface.userTab.dailyCard.updateProgress(achievementState.currentExp, index)
        } else {
            if (!global) {
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

    fun notifyLevel(userLevel: Int){
        MyNotifier.notifyLevelUp(project, userLevel)
    }

    companion object {
        lateinit var project: Project

        @JvmStatic
        fun getInstance(): AchievementService =
            ApplicationManager.getApplication().getService(AchievementService::class.java)
    }

}