package com.github.paolobd.intellijgamificationplugin.dataClasses

import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement

data class ApplicationState(
    var userState: UserState = UserState(),
    var achievementList: MutableList<AchievementState> = GlobalAchievement.values().map {
        AchievementState(it.achievement.id, 0)
    }.toMutableList(),
    var dailyAchievement: DailyAchievementState = DailyAchievementState(),
    var timestamp: Long = 0
)