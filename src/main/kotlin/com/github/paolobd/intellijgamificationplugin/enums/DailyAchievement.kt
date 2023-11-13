package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class DailyAchievement(
    val achievement: Achievement
){
    DAILY_LOCATOR(
        Achievement(
            1,
            "Inspector Gadget",
            "WebElements found using<br/>any locator strategy",
            "locator.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_NAVIGATION(
        Achievement(
            2,
            "The Web Surfer",
            "Websites visited",
            "navigation.svg",
            listOf(3),
            listOf(20)
        )
    ),
    DAILY_CLICK(
        Achievement(
            3,
            "Click Addicted",
            "Click method called<br/>on WebElements",
            "element_click.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_EARLY(
        Achievement(
            4,
            "Morning Person",
            "Execute a new test between<br/>6 a.m. and 8 a.m.",
            "early.svg",
            listOf(1),
            listOf(20)
        )
    ),
    DAILY_LATE(
        Achievement(
            5,
            "Night Owl",
            "Execute a new test between<br/>11 p.m. and 3 a.m.",
            "late.svg",
            listOf(1),
            listOf(20)
        )
    ),
    DAILY_TEST_PASSED(
        Achievement(
            6,
            "Can't believe it worked!",
            "Selenium tests passed",
            "test_passed.svg",
            listOf(3),
            listOf(20)
        )
    ),
    DAILY_SEND_KEYS(
        Achievement(
            7,
            "Keyboard Master",
            "Send_keys method<br/>called on WebElements",
            "element_send_keys.svg",
            listOf(3),
            listOf(20)
        )
    )
}