package com.github.paolobd.intellijplugintemplate.objects

data class ApplicationState (
    var userState: UserState = UserState(),
    var globalAchievements: List<AchievementState> = GlobalAchievement.values().map{
        AchievementState(it.ordinal, 0, false)
    }
)

data class UserState (
    var name: String = "",
    var iconId: Int = 0,
    var titleId: Int = 0,
    var level: Int = 1,
    var experience: Int = 0,
    var showcase: List<Int> = listOf(0, 0, 0, 0, 0)
)