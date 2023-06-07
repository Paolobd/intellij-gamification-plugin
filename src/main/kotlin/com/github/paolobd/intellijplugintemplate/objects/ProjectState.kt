package com.github.paolobd.intellijplugintemplate.objects

// Var variables or cannot be serialized
data class ProjectState(
    var achievementList: List<AchievementP> = AchievementValues.values().map{
        AchievementP(it.achievementName, it.achievementDescription, it.iconName, it.maxExp)
    }.toList()
)

// Leave default values or can't be serialized
data class AchievementP(
    var achievementName: String = "",
    var achievementDescription: String = "",
    var iconName: String = "",
    var maxExp: Int = 0,
    var minExp: Int = 0,
    var currentExp: Int = 0
){
   constructor(achievementName: String, achievementDescription: String, iconName: String, maxExp: Int) :
           this(achievementName, achievementDescription, iconName, maxExp, 0, 0)
}

enum class AchievementValues(
    val achievementName: String,
    val achievementDescription: String,
    val iconName: String,
    val maxExp: Int,
    ){
    NUM_CLICKS("Number of clicks",
        "Number of button clicks you've made in this project",
        "userInterface/BronzeTrophy.svg",
        100),
    NUM_SITES("Number of sites",
        "Number of sites you've visited in this project",
        "userInterface/SilverTrophy.png",
        50)
}