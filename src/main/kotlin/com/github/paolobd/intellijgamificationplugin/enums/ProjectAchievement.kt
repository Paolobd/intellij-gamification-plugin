package com.github.paolobd.intellijgamificationplugin.enums

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement

enum class ProjectAchievement(
    val achievement: Achievement
) {
    NUM_CLICKS(
        Achievement(
            "Button Masher",
            "Number of clicks made in this project",
            "click.svg",
            10,
            20
        )
    ),
    NUM_SITES(
        Achievement(
            "Web Surfer",
            "Number of sites visited in this project",
            "site.svg",
            5,
            20
        )
    ),
    NUM_LOCATOR_ALL(
        Achievement(
            "Gotta find 'em all!",
            "Total of WebElements found",
            "locator.svg",
            200,
            30
        )
    ),
    NUM_LOCATOR_ID(
        Achievement(
            "ID Expert",
            "Different WebElements found using id",
            "id.svg",
            100,
            30
        )
    ),
    NUM_LOCATOR_XPATH(
        Achievement(
            "XPATH Expert",
            "Different WebElements found using xpath",
            "xpath.svg",
            100,
            30
        )
    ),
    NUM_LOCATOR_CSS(
        Achievement(
            "CSS Expert",
            "Different WebElements found using css",
            "css.svg",
            100,
            30
        )
    ),
    NUM_NAVIGATION(
        Achievement(
            "Navigation Wizard",
            "Number of navigation commands used:<br/> Refresh/Back/Forward",
            "navigation.svg",
            10,
            50
        )
    ),
    NUM_LOGIN(
        Achievement(
            "Login",
            "Login once",
            "login.svg",
            1,
            100
        )
    )
}