package com.github.paolobd.intellijplugintemplate.objects

enum class ProjectAchievement(
    val achievement: Achievement
) {
    NUM_CLICKS(
        Achievement(
            "Button Masher",
            "Number of clicks made in this project",
            "/userInterface/project/click.svg",
            10,
            20
        )
    ),
    NUM_SITES(
        Achievement(
            "Web Surfer",
            "Number of sites visited in this project",
            "/userInterface/project/site.svg",
            5,
            20
        )
    ),
    NUM_LOCATOR_ALL(
        Achievement(
            "Gotta find 'em all!",
            "Total of WebElements found",
            "/userInterface/project/locator.svg",
            200,
            30
        )
    ),
    NUM_LOCATOR_ID(
        Achievement(
            "ID Expert",
            "Different WebElements found using id",
            "/userInterface/project/id.svg",
            100,
            30
        )
    ),
    NUM_LOCATOR_XPATH(
        Achievement(
            "XPATH Expert",
            "Different WebElements found using xpath",
            "/userInterface/project/xpath.svg",
            100,
            30
        )
    ),
    NUM_LOCATOR_CSS(
        Achievement(
            "CSS Expert",
            "Different WebElements found using css",
            "/userInterface/project/css.svg",
            100,
            30
        )
    ),
}