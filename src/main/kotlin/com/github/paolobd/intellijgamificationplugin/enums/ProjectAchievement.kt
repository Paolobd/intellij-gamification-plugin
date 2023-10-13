package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class ProjectAchievement(
    val achievement: Achievement
) {
    NUM_CLICKS(
        Achievement(
            1,
            "Button Masher",
            "Number of clicks made in this project",
            "click.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_SITES(
        Achievement(
            2,
            "Web Surfer",
            "Number of sites visited in this project",
            "site.svg",
            listOf(5, 10, 50),
            listOf(10, 20, 30)
        )
    ),
    NUM_LOCATOR_ALL(
        Achievement(
            3,
            "Gotta find 'em all!",
            "Total of WebElements found",
            "locator.svg",
            listOf(20, 50, 100),
            listOf(30, 50, 70)
        )
    ),
    NUM_LOCATOR_ID(
        Achievement(
            4,
            "ID Expert",
            "Different WebElements found using id",
            "id.svg",
            listOf(100),
            listOf(30)
        )
    ),
    NUM_LOCATOR_XPATH(
        Achievement(
            5,
            "XPATH Expert",
            "Different WebElements found using xpath",
            "xpath.svg",
            listOf(100),
            listOf(30)
        )
    ),
    NUM_LOCATOR_CSS(
        Achievement(
            6,
            "CSS Expert",
            "Different WebElements found using css",
            "css.svg",
            listOf(100),
            listOf(30)
        )
    ),
    NUM_NAVIGATION(
        Achievement(
            7,
            "Navigation Wizard",
            "Number of navigation commands used:<br/> Refresh/Back/Forward",
            "navigation.svg",
            listOf(10),
            listOf(50)
        )
    ),
    NUM_LOGIN(
        Achievement(
            8,
            "Login",
            "Login once",
            "login.svg",
            listOf(1),
            listOf(100)
        )
    )
}