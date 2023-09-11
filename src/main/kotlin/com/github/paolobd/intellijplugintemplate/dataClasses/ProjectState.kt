package com.github.paolobd.intellijplugintemplate.dataClasses

import com.github.paolobd.intellijplugintemplate.library.Event
import com.github.paolobd.intellijplugintemplate.enums.ProjectAchievement

// Var variables or cannot be serialized
data class ProjectState(
    /*var achievementList: List<AchievementState> = AchievementValues.values().map{
        AchievementState(it.achievementName, it.achievementDescription, it.iconName, it.maxExp)
    }.toList()*/
    //var achievementList: MutableMap<Int, Int> = AchievementList.values().map { it.ordinal }.associateWith { 0 }.toMutableMap()

    var achievementList: List<AchievementState> = ProjectAchievement.values().map{
        AchievementState(it.ordinal, 0, false)
    },
    var eventList: MutableList<Event> = mutableListOf()
)
