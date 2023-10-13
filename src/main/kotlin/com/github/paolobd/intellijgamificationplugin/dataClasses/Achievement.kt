package com.github.paolobd.intellijgamificationplugin.dataClasses

data class Achievement(
    val id: Int,
    val name: String,
    val description: String,
    val iconPath: String,
    val milestone: List<Int>,
    val userExperience: List<Int>
)