package com.github.paolobd.intellijgamificationplugin.dataClasses

import com.github.paolobd.intellijgamificationplugin.communication.Event
import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement

// Var variables or cannot be serialized
data class ProjectState(
    var achievementList: MutableList<AchievementState> = ProjectAchievement.values().map {
        AchievementState(it.achievement.id, 0)
    }.toMutableList(),
    var eventList: MutableList<Event> = mutableListOf(),
    var testState: MutableMap<String, Boolean> = mutableMapOf(),
    var timestamp: Long = 0
)
