package com.github.paolobd.intellijgamificationplugin.dataProviders

import com.github.paolobd.intellijgamificationplugin.dataClasses.UserIcon

object UserIconDataProvider {
    val icons = listOf(
        UserIcon("user.svg", 1),
        UserIcon("leaf.svg", 1),
        UserIcon("snow.svg", 1),
        UserIcon("star.svg", 1),
        UserIcon("thunder.svg", 1),
        UserIcon("car.svg", 2),
        UserIcon("cat.svg", 2),
        UserIcon("dog.svg", 2),
        UserIcon("dice.svg", 3),
        UserIcon("film.svg", 3),
        UserIcon("joystick.svg", 3),
        UserIcon("bow.svg", 4),
        UserIcon("shield.svg", 4),
        UserIcon("sword.svg", 4),
        UserIcon("airplane.svg", 5),
        UserIcon("cake.svg", 5),
        UserIcon("keyboard.svg", 5),
        UserIcon("book.svg", 6),
        UserIcon("candy.svg", 6),
        UserIcon("pizza.svg", 6),
        UserIcon("mouse.svg", 7),
        UserIcon("music.svg", 7),
        UserIcon("ramen.svg", 7),
        UserIcon("fox.svg", 8),
        UserIcon("laptop.svg", 8),
        UserIcon("rocket.svg", 8),
        UserIcon("controller.svg", 9),
        UserIcon("owl.svg", 9),
        UserIcon("cap.svg", 10),
        UserIcon("robot.svg", 10)
    ).sortedBy { it.level }

    fun getUserIconById(id: Int): UserIcon {
        if (id >= 0 && id < icons.size) {
            return icons[id]
        }
        return icons[0]
    }

}