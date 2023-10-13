package com.github.paolobd.intellijgamificationplugin.dataClasses

//AchievementState is used in ProjectState and ApplicationState, hence each parameter must have a default value
data class AchievementState(
    var id: Int = 0,
    var currentExp: Int = 0
)
