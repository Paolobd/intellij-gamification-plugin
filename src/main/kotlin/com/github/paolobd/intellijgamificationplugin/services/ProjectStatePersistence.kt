package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.github.paolobd.intellijgamificationplugin.dataClasses.AchievementState
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.dataClasses.ProjectState
import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.userInterface.MyNotifier
import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import javax.annotation.Nullable

@State(name = "ProjectState", storages = [Storage("GamificationGUIProjectState.xml")])
class ProjectStatePersistence : PersistentStateComponent<ProjectState> {

    private var myProjectState: ProjectState = ProjectState()

    @Nullable
    override fun getState(): ProjectState {
        return myProjectState
    }

    override fun loadState(@NotNull state: ProjectState) {
        XmlSerializerUtil.copyBean(state, myProjectState)
    }

    fun addMissing() {
        for (achievement in ProjectAchievement.values().map { it.achievement }) {
            val found = myProjectState.achievementList.find { it.id == achievement.id }

            if (found == null) {
                myProjectState.achievementList.add(AchievementState(achievement.id, 0))
            }
        }
    }

    fun resetState() {
        myProjectState = ProjectState()
        for (projectAchievementCard in UserInterface.achievementTab.projectAchievementCards) {
            projectAchievementCard.updateProgress(0, 0)
        }
    }

    /*fun addExp(project: Project, achievement: Achievement, exp: Int) {
        //achEnum contains achievement Name, Description, maxExp, iconName
        //achievement contains currentExp
        val achievementState = myProjectState.achievementList.first { it.id == achievement.id }
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
            MyNotifier.notifyAchievementMilestone(project, achievement, index)
            ApplicationStatePersistence.getInstance().addUserExp(achievement.userExperience[index])
            index++
        }

        //only reached if we have more exp than required for the final milestone
        if (newExp >= achievement.milestone[index] && oldExp != achievement.milestone[index]) {
            newExp = achievement.milestone[index]
            MyNotifier.notifyAchievementMilestone(project, achievement, index)
            ApplicationStatePersistence.getInstance().addUserExp(achievement.userExperience[index - 1])
        } else {
            val oldPer = oldExp.toFloat() / achievement.milestone[index] * 100
            val newPer = newExp.toFloat() / achievement.milestone[index] * 100
            val oldRounded = (oldPer / 25).toInt() * 25
            val newRounded = (newPer / 25).toInt() * 25

            if (newRounded > 0 && newRounded > oldRounded) {
                MyNotifier.notifyAchievementProgress(project, achievement, index, newRounded)
            }
        }

        achievementState.currentExp = newExp

        //update the value in the UI
        UserInterface.achievementTab.updateProjectAchievement(achievement.id, achievementState.currentExp, index)
    }*/

    companion object {
        @JvmStatic
        fun getInstance(project: Project): ProjectStatePersistence =
            project.getService(ProjectStatePersistence::class.java)
    }
}