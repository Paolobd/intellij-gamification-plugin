package com.github.paolobd.intellijplugintemplate.services

import com.github.paolobd.intellijplugintemplate.objects.AchievementCard
import com.github.paolobd.intellijplugintemplate.objects.ProjectAchievementList
import com.github.paolobd.intellijplugintemplate.objects.ProjectState
import com.github.paolobd.intellijplugintemplate.views.MyNotifier
import com.github.paolobd.intellijplugintemplate.views.UserInterface
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
        //println("Project State: $myProjectState")
        return myProjectState
    }

    override fun loadState(@NotNull state: ProjectState) {
        XmlSerializerUtil.copyBean(state, myProjectState)
    }

    fun resetState() {
        myProjectState = ProjectState()
        AchievementCard.getList().forEach{ it.updateProgress(0) }
    }

    fun addExp(achEnum: ProjectAchievementList, exp: Int) {

        //achEnum contains achievement Name, Description, maxExp, iconName
        //achievement contains currentExp
        //val achievement = myProjectState.achievementList.entries.first { it.key == achEnum.ordinal }
        val achievement = myProjectState.achievementList.first{ it.id == achEnum.ordinal }

        //val oldExp = achievement.value

        val oldExp = achievement.currentExp

        //update the value in the state
       /* achievement.setValue(
            if (achievement.value + exp > achEnum.maxExp) achEnum.maxExp
            else achievement.value + exp
        )*/

        achievement.currentExp = if (achievement.currentExp + exp > achEnum.total) achEnum.total
            else achievement.currentExp + exp

        //update the value in the UI
        //AchievementCard.getList().first { achEnum.ordinal == it.id }.updateProgress(achievement.value)
        //AchievementCard.getList().first { achEnum.ordinal == it.id }.updateProgress(achievement.currentExp)
        UserInterface.projectAchievementCards.first { achEnum.ordinal == it.id }.updateProgress(achievement.currentExp)

        //prepare to send the notification
        var notificationText: String? = null
        val oldPercentage = oldExp.toFloat() / achEnum.total * 100
        //val currPercentage = achievement.value.toFloat() / achEnum.maxExp * 100
        val currPercentage = achievement.currentExp.toFloat() / achEnum.total * 100

        intArrayOf(25, 50, 75, 100).forEach {
            if (oldPercentage < it && currPercentage >= it) {
                notificationText =
                    if (it == 100) "Congratulations! You've completed '${achEnum.title}'"
                    else "You've reached $it% of '${achEnum.title}'"
            }
        }

        //Passing null instead of project makes it appear in any case, if I manage to pass the project it will show only
        //in the project windows
        if (notificationText != null) {
            MyNotifier.notifyAchievement(null, notificationText!!)
        }
    }

    companion object {
        @JvmStatic
        fun getInstance(project: Project): ProjectStatePersistence = project.getService(ProjectStatePersistence::class.java)

    }
}