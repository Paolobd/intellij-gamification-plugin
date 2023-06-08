package com.github.paolobd.intellijplugintemplate.objects

// Var variables or cannot be serialized
data class ProjectState(
    /*var achievementList: List<Achievement> = AchievementValues.values().map{
        Achievement(it.achievementName, it.achievementDescription, it.iconName, it.maxExp)
    }.toList()*/
    var achievementList: MutableMap<Int, Int> =
        AchievementList.values().map { it.ordinal }.associateWith { 0 }.toMutableMap()
)

// Leave default values or can't be serialized
//Not used anymore because we save just the achievement id and current exp. Maybe used in the future to have a
//more complex value (e.g. currentexp, unlocked true false)
data class Achievement(
    var achievementName: String = "",
    var achievementDescription: String = "",
    var iconName: String = "",
    var maxExp: Int = 0,
    var minExp: Int = 0,
    var currentExp: Int = 0
) {
    constructor(achievementName: String, achievementDescription: String, iconName: String, maxExp: Int) :
            this(achievementName, achievementDescription, iconName, maxExp, 0, 0)
}