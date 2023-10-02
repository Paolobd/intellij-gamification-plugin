package com.github.paolobd.intellijgamificationplugin.dataProviders

import com.github.paolobd.intellijgamificationplugin.dataClasses.UserIcon

class UserIconDataProvider {
    val icons = listOf(
        UserIcon("user.svg", 1),
        UserIcon("airplane.svg", 5),
        UserIcon("book.svg", 6),
        UserIcon("bow.svg", 4),
        UserIcon("cake.svg", 5),
        UserIcon("candy.svg", 6),
        UserIcon("cap.svg", 10),
        UserIcon("car.svg", 2),
        UserIcon("cat.svg", 2),
        UserIcon("controller.svg", 9),
        UserIcon("dice.svg", 3),
        UserIcon("dog.svg", 2),
        UserIcon("film.svg", 3),
        UserIcon("fox.svg", 8),
        UserIcon("joystick.svg", 3),
        UserIcon("keyboard.svg", 5),
        UserIcon("laptop.svg", 8),
        UserIcon("leaf.svg", 1),
        UserIcon("mouse.svg", 7),
        UserIcon("music.svg", 7),
        UserIcon("owl.svg", 9),
        UserIcon("pizza.svg", 6),
        UserIcon("ramen.svg", 7),
        UserIcon("robot.svg", 10),
        UserIcon("rocket.svg", 8),
        UserIcon("shield.svg", 4),
        UserIcon("snow.svg", 1),
        UserIcon("star.svg", 1),
        UserIcon("sword.svg", 4),
        UserIcon("thunder.svg", 1)
    ).sortedBy { it.level }

    fun getUserIconById(id: Int): UserIcon {
        if (id >= 0 && id < icons.size) {
            return icons[id]
        }
        return icons[0]
    }

}