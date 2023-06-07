package com.github.paolobd.intellijplugintemplate.objects

import com.intellij.openapi.util.IconLoader

class AchievementList {

    fun getAchievementList(): MutableList<Achievement>{
        return list
    }

    companion object Initialize{
        val list = mutableListOf<Achievement>()

        init {
            val bronzeTrophy = IconLoader.getIcon("userInterface/BronzeTrophy.svg", Initialize::class.java)
            val silverTrophy = IconLoader.getIcon("userInterface/SilverTrophy.svg", Initialize::class.java)
            val goldTrophy = IconLoader.getIcon("userInterface/GoldTrophy.svg", Initialize::class.java)

            list.add(
                Achievement(1, goldTrophy, "Click!!!", "Click the buttons", 0, 10)
            )
        }
    }

}