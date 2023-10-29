package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.github.paolobd.intellijgamificationplugin.services.ProjectStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBTabbedPane
import javax.swing.JButton
import javax.swing.JPanel


class UserInterface(project: Project) {

    init {
        mainUI = JBTabbedPane()
        UserInterface.project = project
        userTab = UserTab()
        achievementTab = AchievementsTab(project)

        mainUI.addTab("Profile", userTab.toolWindow)
        mainUI.addTab("Achievements", achievementTab.toolWindow)
        mainUI.addTab("Debug", debugButton())
    }

    fun getContent(): JBTabbedPane {
        return mainUI
    }

    private fun debugButton(): JPanel {
        val panel = JPanel()
        val button = JButton("Click me!")

        button.addActionListener {
            ApplicationStatePersistence.getInstance().resetState()
        }
        panel.add(button)
        return panel
    }

    companion object {
        private lateinit var project: Project
        private lateinit var mainUI: JBTabbedPane
        lateinit var userTab: UserTab
        lateinit var achievementTab: AchievementsTab

        fun resetWindow() {
            val applicationState = ApplicationStatePersistence.getInstance()
            val projectState = ProjectStatePersistence.getInstance(project)

            applicationState.addMissingAndCheckDaily()
            projectState.addMissing(applicationState.state.timestamp)

            userTab = UserTab()
            achievementTab = AchievementsTab(project)

            mainUI.setComponentAt(0, userTab.toolWindow)
            mainUI.setComponentAt(1, achievementTab.toolWindow)
        }
    }
}