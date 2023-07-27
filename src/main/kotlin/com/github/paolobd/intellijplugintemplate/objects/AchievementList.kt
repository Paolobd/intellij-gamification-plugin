package com.github.paolobd.intellijplugintemplate.objects

enum class AchievementList(
    val achievementName: String,
    val achievementDescription: String,
    val iconName: String,
    val maxExp: Int
) {
    NUM_CLICKS(
        "Number of clicks",
        "Number of button clicks you've made in this project",
        "/userInterface/BronzeTrophy.svg",
        100
    ),
    NUM_SITES(
        "Number of sites",
        "Number of sites you've visited in this project",
        "/userInterface/SilverTrophy.png",
        50
    ),
    NUM_LOCATOR_ALL(
        "Elements found using locators",
        "Number of WebElements found",
        "/userInterface/GoldTrophy.png",
        200
    ),
    NUM_LOCATOR_ID(
        "Elements found by Id",
        "Number of WebElements found using an id",
        "/userInterface/SilverTrophy.png",
        100
    ),
    NUM_LOCATOR_XPATH(
        "Elements found by Xpath",
        "Number of WebElements found using xpath",
        "/userInterface/SilverTrophy.png",
        100
    ),
    NUM_LOCATOR_CSS(
        "Elements found by CSS",
        "Number of WebElements found using css",
        "/userInterface/SilverTrophy.png",
        100
    ),
}