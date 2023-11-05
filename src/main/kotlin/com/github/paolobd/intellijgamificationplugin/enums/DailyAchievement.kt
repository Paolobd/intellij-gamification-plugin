package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class DailyAchievement(
    val achievement: Achievement
){
    DAILY_LOCATOR(
        Achievement(
            1,
            "Inspector Gadget",
            "Different WebElements found<br/>using any locator strategy",
            "locator.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_NAVIGATION(
        Achievement(
            2,
            "The Web Surfer",
            "Different websites visited",
            "navigation.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_CLICK(
        Achievement(
            3,
            "Click Addicted",
            "Times the 'click()' method<br/>was called on different WebElements",
            "element_click.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_EARLY(
        Achievement(
            4,
            "Morning Person",
            "Execute a test between 6 a.m. and 8 a.m.",
            "early.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_LATE(
        Achievement(
            5,
            "Night Owl",
            "Execute a test between 11 p.m. and 3 a.m.",
            "late.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_TEST_PASSED(
        Achievement(
            6,
            "Can't believe it worked!",
            "Different Selenium tests passed",
            "test_passed.svg",
            listOf(5),
            listOf(20)
        )
    ),
    DAILY_SEND_KEYS(
        Achievement(
            7,
            "Keyboard Master",
            "Times the 'send_keys(...)' method<br/>was called on different WebElements",
            "element_send_keys.svg",
            listOf(5),
            listOf(20)
        )
    )
}