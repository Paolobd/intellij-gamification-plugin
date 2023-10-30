package com.github.paolobd.intellijgamificationplugin.dataProviders

import com.github.paolobd.intellijgamificationplugin.dataClasses.UserIcon

object UserIconDataProvider {
    val icons = listOf(
        UserIcon(1, "user.svg", 1),
        UserIcon(2, "leaf.svg", 1),
        UserIcon(3, "snow.svg", 1),
        UserIcon(4, "star.svg", 1),
        UserIcon(5, "thunder.svg", 1),
        UserIcon(6, "car.svg", 2),
        UserIcon(7, "cat.svg", 2),
        UserIcon(8, "dog.svg", 2),
        UserIcon(9, "dice.svg", 3),
        UserIcon(10, "film.svg", 3),
        UserIcon(11, "joystick.svg", 3),
        UserIcon(12, "bow.svg", 4),
        UserIcon(13, "shield.svg", 4),
        UserIcon(14, "sword.svg", 4),
        UserIcon(15, "airplane.svg", 5),
        UserIcon(16, "cake.svg", 5),
        UserIcon(17, "keyboard.svg", 5),
        UserIcon(18, "book.svg", 6),
        UserIcon(19, "candy.svg", 6),
        UserIcon(20, "pizza.svg", 6),
        UserIcon(21, "mouse.svg", 7),
        UserIcon(22, "music.svg", 7),
        UserIcon(23, "ramen.svg", 7),
        UserIcon(24, "fox.svg", 8),
        UserIcon(25, "laptop.svg", 8),
        UserIcon(26, "rocket.svg", 8),
        UserIcon(27, "controller.svg", 9),
        UserIcon(28, "owl.svg", 9),
        UserIcon(29, "cap.svg", 10),
        UserIcon(30, "robot.svg", 10)
    ).sortedBy { it.level }

    fun getUserIconById(id: Int): UserIcon {
        val found = icons.find { it.id == id }

        return found ?: icons.first { it.id == 1 }
    }

}