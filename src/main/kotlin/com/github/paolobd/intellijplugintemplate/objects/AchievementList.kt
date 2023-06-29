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
    )
}