package com.github.paolobd.intellijgamificationplugin.dataClasses

//UserState is used in ApplicationState, hence each parameter must have a default value
data class UserState (
    var name: String = "User",
    var iconId: Int = 0,
    var titleId: Int = 0,
    var level: Int = 1,
    var experience: Int = 0,
    var showcase: List<Int> = listOf(-1, -1, -1, -1, -1)
)