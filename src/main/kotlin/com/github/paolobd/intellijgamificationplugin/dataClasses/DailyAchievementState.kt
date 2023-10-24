package com.github.paolobd.intellijgamificationplugin.dataClasses

data class DailyAchievementState(
    var state: AchievementState = AchievementState(),
    var timestamp: Long = 0
)
