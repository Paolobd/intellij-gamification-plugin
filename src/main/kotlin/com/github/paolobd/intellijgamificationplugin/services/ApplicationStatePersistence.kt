package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.ApplicationState
import com.github.paolobd.intellijgamificationplugin.dataProviders.LevelDataProvider
import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import javax.annotation.Nullable

@State(name = "ApplicationState", storages = [Storage("GamificationGUIApplicationState.xml")])
class ApplicationStatePersistence : PersistentStateComponent<ApplicationState> {

    private var myApplicationState = ApplicationState()

    @Nullable
    override fun getState(): ApplicationState {
        return myApplicationState
    }

    override fun loadState(@NotNull state: ApplicationState) {
        XmlSerializerUtil.copyBean(state, myApplicationState)
    }

    fun changeUserInfo(name: String, titleId: Int, iconId: Int) {
        myApplicationState.userState.name = name
        myApplicationState.userState.titleId = titleId
        myApplicationState.userState.iconId = iconId
        UserInterface.userTab.updateUserInfo()
    }

    fun changeShowcase(showcase: List<Int>) {
        myApplicationState.userState.showcase = showcase
        UserInterface.userTab.updateUserShowcase()
    }

    fun addUserExp(experience: Int) {
        var experienceToAdd = myApplicationState.userState.experience + experience
        var userLevel = myApplicationState.userState.level
        val levels = LevelDataProvider.levels

        var i = userLevel - 1
        while (i < levels.size && experienceToAdd >= levels[i]) {
            experienceToAdd -= levels[i]
            userLevel++
            i++
        }

        if (i >= levels.size) {
            userLevel = levels.size
            experienceToAdd = levels[levels.size - 1]
        }

        myApplicationState.userState.level = userLevel
        myApplicationState.userState.experience = experienceToAdd
        UserInterface.userTab.updateUserExperience()
    }

    fun resetState() {
        myApplicationState = ApplicationState()
    }

    companion object {
        @JvmStatic
        fun getInstance(): ApplicationStatePersistence =
            ApplicationManager.getApplication().getService(ApplicationStatePersistence::class.java)
    }
}