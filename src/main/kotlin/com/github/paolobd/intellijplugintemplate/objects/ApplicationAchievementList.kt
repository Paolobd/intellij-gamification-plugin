package com.github.paolobd.intellijplugintemplate.objects

enum class ApplicationAchievementList (
    val title: String,
    val description: String,
    val iconUrl: String,
    val total: Int,
    val userExp: Int
) {
    NUM_CLICKS(
        "Number of clicks",
        "Number of button clicks you've made in total",
        "/userInterface/click.svg",
        10,
        20
    ),
    NUM_SITES(
        "Number of sites",
        "Number of sites you've visited",
        "/userInterface/sites.svg",
        5,
        20
    ),
    NUM_LOCATOR_ALL(
        "Elements found using locators",
        "Number of WebElements found",
        "/userInterface/GoldTrophy.png",
        200,
        30
    ),
    NUM_LOCATOR_ID(
        "Elements found by Id",
        "Number of WebElements found using an id",
        "/userInterface/SilverTrophy.png",
        100,
        30
    ),
    NUM_LOCATOR_XPATH(
        "Elements found by Xpath",
        "Number of WebElements found using xpath",
        "/userInterface/SilverTrophy.png",
        100,
        30
    ),
    NUM_LOCATOR_CSS(
        "Elements found by CSS",
        "Number of WebElements found using css",
        "/userInterface/SilverTrophy.png",
        100,
        30
    ),
}
