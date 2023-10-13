package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.dataClasses.ProjectState
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

    fun resetState() {
        myProjectState = ProjectState()
        for (projectAchievementCard in UserInterface.achievementTab.projectAchievementCards) {
            projectAchievementCard.updateProgress(0, 0)
        }
    }

    fun addExp(achievement: Achievement, exp: Int) {

        //achEnum contains achievement Name, Description, maxExp, iconName
        //achievement contains currentExp
        val achievementState = myProjectState.achievementList.first{ it.id == achievement.id }

        //val oldExp = achievement.value

        val oldExp = achievementState.currentExp

        //update the value in the state
        var index = 0
        var newExp = oldExp + exp

        while(index < achievement.milestone.size-1 && newExp >= achievement.milestone[index]){
            index++
        }

        //only reached if we have more exp than required for the final milestone
        if(newExp > achievement.milestone[index]){
            newExp = achievement.milestone[index]
        }

        achievementState.currentExp = newExp

        //update the value in the UI
        UserInterface.achievementTab.updateProjectAchievement(achievement.id, achievementState.currentExp, index)

        //prepare to send the notification
        //var notificationText: String? = null
        //val oldPercentage = oldExp.toFloat() / achievement.milestone * 100
        //val currPercentage = achievement.value.toFloat() / achEnum.maxExp * 100
        //val currPercentage = achievementState.currentExp.toFloat() / achievement.milestone * 100

        /*intArrayOf(25, 50, 75, 100).forEach {
            if (oldPercentage < it && currPercentage >= it) {
                notificationText =
                    if (it == 100) "Congratulations! You've completed '${achievement.name}'"
                    else "You've reached $it% of '${achievement.name}'"
            }
        }*/

        //Passing null instead of project makes it appear in any case, if I manage to pass the project it will show only
        //in the project windows
       /* if (notificationText != null) {
            MyNotifier.notifyAchievement(null, notificationText!!)
        }*/
    }

    companion object {
        @JvmStatic
        fun getInstance(project: Project): ProjectStatePersistence = project.getService(ProjectStatePersistence::class.java)

    }
}