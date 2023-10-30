package com.github.paolobd.intellijgamificationplugin.dataProviders

import com.github.paolobd.intellijgamificationplugin.dataClasses.Title

object TitleDataProvider {
    val titles = listOf(
        Title(1, "Novice", 1),
        Title(2, "Always asleep", 1),
        Title(3, "Learning to test", 2),
        Title(4, "How does it work?", 2),
        Title(5, "Now I get it!", 3),
        Title(6, "GUI Apprentice", 3),
        Title(7, "... I don't really get it", 4),
        Title(8, "Gamification is fun", 4),
        Title(9, "Bug bounty hunter", 5),
        Title(10, "Never give up", 5),
        Title(11, "Testing artisan", 6),
        Title(12, "Master debugger", 6),
        Title(13, "Web wizard", 7),
        Title(14, "Code Ninja", 7),
        Title(15, "UI Virtuoso", 8),
        Title(16, "Selenium Connoisseur", 8),
        Title(17, "Gamification Enthusiast", 9),
        Title(18, "Game Changer", 9),
        Title(19, "GUI Testing Wizard", 10),
        Title(20, "The cake is a lie", 10)
    ).sortedBy { it.level }

    fun getTitleById(id: Int): Title {
        val found = titles.find { it.id == id }

        return found ?: titles.first { it.id == 1 }
    }

    fun getIndexById(id: Int): Int {
        val found = titles.indexOfFirst { it.id == id }

        if (found >= 0) {
            return found
        }
        return 0
    }
}