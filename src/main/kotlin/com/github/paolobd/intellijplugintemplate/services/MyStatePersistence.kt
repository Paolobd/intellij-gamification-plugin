package com.github.paolobd.intellijplugintemplate.services

import com.github.paolobd.intellijplugintemplate.objects.AchievementList
import com.github.paolobd.intellijplugintemplate.objects.AchievementUI
import com.github.paolobd.intellijplugintemplate.objects.ProjectState
import com.github.paolobd.intellijplugintemplate.views.MyNotifier
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import javax.annotation.Nullable

@State(name = "ProjectState", storages = [Storage("GamificationGUIProjectState.xml")])
class MyStatePersistence : PersistentStateComponent<ProjectState> {

    private var myProjectState: ProjectState = ProjectState()

    @Nullable
    override fun getState(): ProjectState {
        println("Project State: $myProjectState")
        return myProjectState
    }

    override fun loadState(@NotNull state: ProjectState) {
        XmlSerializerUtil.copyBean(state, myProjectState)
    }

    fun resetState() {
        myProjectState = ProjectState()
        AchievementUI.getList().forEach{ it.updateProgress(0) }
    }

    fun addExp(achEnum: AchievementList, exp: Int) {

        //achEnum contains achievement Name, Description, maxExp, iconName
        //achievement contains currentExp
        val achievement = myProjectState.achievementList.entries.first { it.key == achEnum.ordinal }

        val oldExp = achievement.value

        //update the value in the state
        achievement.setValue(
            if (achievement.value + exp > achEnum.maxExp) achEnum.maxExp
            else achievement.value + exp
        )

        //update the value in the UI
        AchievementUI.getList().first { achEnum.ordinal == it.id }.updateProgress(achievement.value)

        //prepare to send the notification
        var notificationText: String? = null
        val oldPercentage = oldExp.toFloat() / achEnum.maxExp * 100
        val currPercentage = achievement.value.toFloat() / achEnum.maxExp * 100

        intArrayOf(25, 50, 75, 100).forEach {
            if (oldPercentage < it && currPercentage >= it) {
                notificationText =
                    if (it == 100) "Congratulations! You've completed '${achEnum.achievementName}'"
                    else "You've reached $it% of '${achEnum.achievementName}'"
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
        fun getInstance(project: Project): MyStatePersistence = project.getService(MyStatePersistence::class.java)

        //@JvmStatic
        //fun getInstance(project: Project): MyStatePersistence = project.service<MyStatePersistence>()
    }
}