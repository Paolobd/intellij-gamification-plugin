package com.github.paolobd.intellijplugintemplate.userInterface

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBTabbedPane


class UserInterface(private val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()

    init {
        userTab = UserTab()
        achievementTab = AchievementsTab(project)

        mainUI.addTab("Profile", userTab.toolWindow)
        mainUI.addTab("Achievements", achievementTab.toolWindow)
    }

    fun getContent(): JBTabbedPane {
        return mainUI
    }

    companion object {
        lateinit var userTab: UserTab
        lateinit var achievementTab: AchievementsTab
    }
}