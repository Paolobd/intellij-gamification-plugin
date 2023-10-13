package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class GlobalAchievement(
    val achievement: Achievement
) {
    NUM_CLICKS(
        Achievement(
            1,
            "Night Owl",
            "Execute a test between 11 p.m and 3 a.m",
            "owl.svg",
            listOf(1),
            listOf(100)
        )
    ),
    NUM_SITES(
        Achievement(
            2,
            "Slow Testing",
            "A test took more than 1 minute",
            "slow.svg",
            listOf(5),
            listOf(20)
        )
    ),
    NUM_LOCATOR_ALL(
        Achievement(
            3,
            "Fast Testing",
            "A test took more than 1 second",
            "fast.svg",
            listOf(200),
            listOf(30)
        )
    ),
    NUM_LOCATOR_ID(
        Achievement(
            4,
            "High Success!",
            "Number of tests passed",
            "success.svg",
            listOf(100),
            listOf(30)
        )
    ),
    NUM_LOCATOR_XPATH(
        Achievement(
            5,
            "Failure",
            "Number of tests failed",
            "failed.svg",
            listOf(100),
            listOf(30)
        )
    ),
    NUM_LOCATOR_CSS(
        Achievement(
            6,
            "More Testing!",
            "Executed a suite with more than 10 test",
            "test.svg",
            listOf(100),
            listOf(30)
        )
    ),
}
