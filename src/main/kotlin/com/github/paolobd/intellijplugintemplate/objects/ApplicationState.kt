package com.github.paolobd.intellijplugintemplate.objects

data class ApplicationState (
    var userName: String = "",
    var userIconId: Int = 1,
    var userIcons: MutableList<UserIcon> = mutableListOf(),
    var userTitles: MutableList<UserTitle> = mutableListOf(),
    var userTitleId: Int = 1,
    var userLevel: Int = 1,
    var globalAchievements: List<Achievement> = ApplicationAchievementList.values().map{
        Achievement(it.ordinal, 0, false)
    }
)

data class UserIcon(
    var id: Int = 0,
    var unlocked: Boolean = false
)

data class UserTitle(
    var id: Int = 0,
    var unlocked: Boolean = false
)