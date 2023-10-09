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
        500
    )

    fun getLevelExperienceById(id: Int): Int {
        if (id >= 0 && id < levels.size) {
            return levels[id]
        }
        return levels[0]
    }
}