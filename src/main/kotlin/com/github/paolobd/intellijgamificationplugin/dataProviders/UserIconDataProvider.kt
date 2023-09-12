package com.github.paolobd.intellijgamificationplugin.dataProviders

import com.github.paolobd.intellijgamificationplugin.dataClasses.UserIcon

class UserIconDataProvider {
    val icons = listOf(
        UserIcon("user.svg", 1),
        UserIcon("cat.svg", 1)
    )

    fun getUserIconById(id: Int): UserIcon {
        if (id >= 0 && id < icons.size) {
            return icons[id]
        }
        return icons[0]
    }

}