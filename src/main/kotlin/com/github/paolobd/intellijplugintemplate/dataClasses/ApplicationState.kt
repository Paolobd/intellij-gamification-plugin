package com.github.paolobd.intellijplugintemplate.dataClasses

import com.github.paolobd.intellijplugintemplate.enums.GlobalAchievement

data class ApplicationState (
    var userState: UserState = UserState(),
    var globalAchievements: List<AchievementState> = GlobalAchievement.values().map{
        AchievementState(it.ordinal, 0, false)
    }
)