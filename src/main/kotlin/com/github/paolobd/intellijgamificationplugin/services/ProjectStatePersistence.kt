package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.AchievementState
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.dataClasses.ProjectState
import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

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

    companion object {
        @JvmStatic
        fun getInstance(project: Project): ProjectStatePersistence =
            project.getService(ProjectStatePersistence::class.java)
    }
}