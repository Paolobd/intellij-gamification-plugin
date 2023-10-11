package com.github.paolobd.intellijgamificationplugin.dataProviders

class LevelDataProvider {
    val levels = listOf(
        50,
        100,
        150,
        200,
        250,
        300,
        350,
        400,
        450,
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