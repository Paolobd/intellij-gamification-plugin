package com.github.paolobd.intellijgamificationplugin.services

import com.github.paolobd.intellijgamificationplugin.dataClasses.AchievementState
import com.github.paolobd.intellijgamificationplugin.dataClasses.ApplicationState
import com.github.paolobd.intellijgamificationplugin.dataClasses.DailyAchievementState
import com.github.paolobd.intellijgamificationplugin.dataProviders.LevelDataProvider
import com.github.paolobd.intellijgamificationplugin.enums.DailyAchievement
import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.userInterface.MyNotifier
import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

@State(name = "ApplicationState", storages = [Storage("gamegui.xml")])
class ApplicationStatePersistence : PersistentStateComponent<ApplicationState> {

    private var myApplicationState = ApplicationState()

    @Nullable
    override fun getState(): ApplicationState {
        return myApplicationState
    }

    override fun loadState(@NotNull state: ApplicationState) {
        XmlSerializerUtil.copyBean(state, myApplicationState)
    }

    fun addMissingAndCheckDaily() {

        if (myApplicationState.timestamp.toInt() == 0) {
            myApplicationState.timestamp = System.currentTimeMillis()
        }

        for (achievement in GlobalAchievement.values().map { it.achievement }) {
            val found = myApplicationState.achievementList.find { it.id == achievement.id }

            if (found == null) {
                myApplicationState.achievementList.add(AchievementState(achievement.id, 0))
            }
        }
        dailyUpdated()
    }

    fun checkAndUpdateDailyGUI() {
        if (dailyUpdated()) {
            UserInterface.userTab.updateDailyAchievement()
        }
    }

    private fun dailyUpdated(): Boolean {
        val timestampDailyAch = myApplicationState.dailyAchievement.timestamp
        val dateNow = formatTimestamp(System.currentTimeMillis())
        val dateDaily = formatTimestamp(timestampDailyAch)

        if (dateNow > dateDaily) {
            val dailyId = Random.nextInt(1, DailyAchievement.values().size + 1)
            myApplicationState.dailyAchievement = DailyAchievementState(
                AchievementState(dailyId, 0), System.currentTimeMillis()
            )
            return true
        }
        return false
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
        if (experience == 0) {
            return
        }

        var experienceToAdd = myApplicationState.userState.experience + experience
        var userLevel = myApplicationState.userState.level
        val levels = LevelDataProvider.levels

        var i = userLevel - 1
        while (i < levels.size && experienceToAdd >= levels[i]) {
            experienceToAdd -= levels[i]
            userLevel++
            i++
            MyNotifier.notifyLevelUp(null, userLevel)
        }

        if (i >= levels.size) {
            userLevel = levels.size
            experienceToAdd = levels[levels.size - 1]
        }

        myApplicationState.userState.level = userLevel
        myApplicationState.userState.experience = experienceToAdd
        UserInterface.userTab.updateUserExperience()
    }

    fun resetStateAndRefresh() {
        myApplicationState = ApplicationState()
        UserInterface.resetWindow()
    }

    fun resetState() {
        myApplicationState = ApplicationState()
    }

    private fun formatTimestamp(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = Date(timestamp)
        return dateFormat.format(date)
    }

    fun formatAvailableDate(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy")
        val date = Date(timestamp)
        return dateFormat.format(date)
    }

    companion object {
        @JvmStatic
        fun getInstance(): ApplicationStatePersistence =
            ApplicationManager.getApplication().getService(ApplicationStatePersistence::class.java)
    }
}