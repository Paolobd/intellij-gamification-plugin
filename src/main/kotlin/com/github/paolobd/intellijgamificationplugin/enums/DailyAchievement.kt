package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class DailyAchievement(
    val achievement: Achievement
){
    DAILY_XPATH(
        Achievement(
            "Xpath enjoyer",
            "Find different xpath elements",
            "xpath.svg",
            5,
            20
        )
    )
}