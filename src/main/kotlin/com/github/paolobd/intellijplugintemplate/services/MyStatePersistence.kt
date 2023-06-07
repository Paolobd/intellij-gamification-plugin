package com.github.paolobd.intellijplugintemplate.services

import com.github.paolobd.intellijplugintemplate.objects.AchievementValues
import com.github.paolobd.intellijplugintemplate.objects.ProjectState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import javax.annotation.Nullable

@State(name = "ProjectState", storages = [Storage("GamificationGUIProjectState.xml")])
class MyStatePersistence: PersistentStateComponent<ProjectState> {

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

    fun addExp(ach: AchievementValues, exp: Int){
        /*val achievement = state.achievementList.first { it.achievementName == ach.achievementName }
        val oldExp = achievement.currentExp
        achievement.currentExp = if (achievement.currentExp + exp > achievement.maxExp) achievement.maxExp
            else achievement.currentExp + exp*/

        //state.achievementList.list.first { it.achievementName == ach.achievementName }.currentExp += exp
        myProjectState.achievementList.first { it.achievementName == ach.achievementName }.currentExp += exp

        //achievement.progressBar!!.value = achievement.currentExp
        //achievement.  progressLabel!!.text = "${achievement.currentExp}/${achievement.maxExp}"
    }

    companion object {
        @JvmStatic
        fun getInstance(project: Project): MyStatePersistence = project.getService(MyStatePersistence::class.java)

        //@JvmStatic
        //fun getProva(project: Project): MyStatePersistence = project.service<MyStatePersistence>()
    }
}