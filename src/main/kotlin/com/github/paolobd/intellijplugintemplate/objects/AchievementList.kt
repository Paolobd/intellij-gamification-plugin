package com.github.paolobd.intellijplugintemplate.objects

import com.intellij.openapi.util.IconLoader

class AchievementList {

    fun getList(): MutableList<Achievement>{
        return list;
    }

    companion object Initialize{
        val list = mutableListOf<Achievement>()

        fun Initialize(){
            val bronzeTrophy = IconLoader.getIcon("userInterface/BronzeTrophy.svg", javaClass)
            val silverTrophy = IconLoader.getIcon("userInterface/SilverTrophy.svg", javaClass)
            val goldTrophy = IconLoader.getIcon("userInterface/GoldTrophy.svg", javaClass)

            list.add(
                Achievement(1, goldTrophy, "Click!!!", "Click the buttons", 0, 20)
            )
        }
    }

}