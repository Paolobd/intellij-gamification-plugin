package com.github.paolobd.intellijgamificationplugin.dataProviders

import com.github.paolobd.intellijgamificationplugin.dataClasses.Title

class TitleDataProvider {
    val titles = listOf(
        Title("Novice", 1),
        Title("Always asleep", 1),
        Title("Learning to test", 2),
        Title("How does it work?", 2),
        Title("Now I get it!", 3),
        Title("GUI Apprentice", 3),
        Title("... I don't really get it", 4),
        Title("Gamification is fun", 4),
        Title("Bug bounty hunter", 5),
        Title("Never give up", 5),
        Title("Testing artisan", 6),
        Title("Master debugger", 6),
        Title("Web wizard", 7),
        Title("Code Ninja", 7),
        Title("UI Virtuoso", 8),
        Title("Selenium Connoisseur", 8),
        Title("Gamification Enthusiast", 9),
        Title("Game Changer", 9),
        Title("GUI Testing Wizard", 10),
        Title("The cake is a lie", 10)
    ).sortedBy { it.level }

    fun getTitleById(id: Int): Title {
        if(id >= 0 && id < titles.size){
            return titles[id]
        }
        return titles[0]
    }
}