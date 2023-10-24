package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class DailyAchievement(
    val achievement: Achievement
){
    DAILY_XPATH(
        Achievement(
            1,
            "Xpath enjoyer",
            "Find different xpath elements",
            "owl.svg",
            listOf(5),
            listOf(20)
        )
    )
}