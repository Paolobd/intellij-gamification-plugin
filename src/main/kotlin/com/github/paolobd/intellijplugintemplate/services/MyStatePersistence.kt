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
    }

    fun addExp(achEnum: AchievementList, exp: Int) {
        /*val achievement = state.achievementList.first { it.achievementName == ach.achievementName }
        val oldExp = achievement.currentExp
        achievement.currentExp = if (achievement.currentExp + exp > achievement.maxExp) achievement.maxExp
            else achievement.currentExp + exp*/

        //state.achievementList.list.first { it.achievementName == ach.achievementName }.currentExp += exp

        //achEnum contains achievement Name, Description, maxExp, iconName
        //achievement contains currentExp
        val achievement = myProjectState.achievementList.entries.first { it.key == achEnum.ordinal }

        val oldExp = achievement.value

        achievement.setValue(
            if (achievement.value + exp > achEnum.maxExp) achEnum.maxExp
            else achievement.value + exp
        )

        AchievementUI.getList().first { achEnum.ordinal == it.id }.updateProgress(achievement.value)

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

        //achievement.progressBar!!.value = achievement.currentExp
        //achievement.  progressLabel!!.text = "${achievement.currentExp}/${achievement.maxExp}"
    }

    companion object {
        @JvmStatic
        fun getInstance(project: Project): MyStatePersistence = project.getService(MyStatePersistence::class.java)

        //@JvmStatic
        //fun getInstance(project: Project): MyStatePersistence = project.service<MyStatePersistence>()
    }
}