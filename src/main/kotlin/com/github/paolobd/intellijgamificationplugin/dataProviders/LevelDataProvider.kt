package com.github.paolobd.intellijgamificationplugin.dataProviders

object LevelDataProvider {
    val levels = listOf(
        50,
        100,
        200,
        300,
        400,
        500,
        600,
        700,
        800,
        9999
    )

    fun getLevelExperienceById(level: Int): Int {
        val newLevel = level - 1
        if (newLevel >= 0 && newLevel < levels.size) {
            return levels[newLevel]
        }
        return levels[0]
    }
}