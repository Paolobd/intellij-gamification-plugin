package com.github.paolobd.intellijgamificationplugin.dataClasses

//AchievementState is used in ProjectState and ApplicationState, hence each parameter must have a default value
data class AchievementState(
    /*var achievementName: String = "",
    var achievementDescription: String = "",
    var iconName: String = "",
    var maxExp: Int = 0,
    var minExp: Int = 0,
    var currentExp: Int = 0*/
    var id: Int = 0,
    var currentExp: Int = 0,
    var unlocked: Boolean = false
)
