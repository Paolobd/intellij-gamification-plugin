package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.AchievementState
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.dataClasses.ProjectState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable

@State(name = "ProjectState", storages = [Storage("gamegui.xml")])
class ProjectStatePersistence : PersistentStateComponent<ProjectState> {

    private var myProjectState: ProjectState = ProjectState()

    @Nullable
    override fun getState(): ProjectState {
        return myProjectState
    }

    override fun loadState(@NotNull state: ProjectState) {
        XmlSerializerUtil.copyBean(state, myProjectState)
    }

    fun addMissing(appTime: Long) {

        if (myProjectState.timestamp < appTime) {
            resetState()
            myProjectState.timestamp = System.currentTimeMillis()
        }

        for (achievement in ProjectAchievement.values().map { it.achievement }) {
            val found = myProjectState.achievementList.find { it.id == achievement.id }

            if (found == null) {
                myProjectState.achievementList.add(AchievementState(achievement.id, 0))
            }
        }
    }

    private fun resetState() {
        myProjectState = ProjectState()
    }

    companion object {
        @JvmStatic
        fun getInstance(project: Project): ProjectStatePersistence =
            project.getService(ProjectStatePersistence::class.java)
    }
}