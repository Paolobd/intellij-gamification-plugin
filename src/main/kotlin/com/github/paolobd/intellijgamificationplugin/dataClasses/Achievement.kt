package com.github.paolobd.intellijplugintemplate.dataClasses

data class Achievement(
    val name: String,
    val description: String,
    val iconPath: String,
    val milestone: Int,
    val userExperience: Int
)