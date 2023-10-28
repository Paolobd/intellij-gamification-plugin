package com.github.paolobd.intellijgamificationplugin.userInterface

import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBTabbedPane
import javax.swing.JButton
import javax.swing.JPanel


class UserInterface(project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()
    //private val project = project

    init {
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
            //ProjectStatePersistence.getInstance(project).addExp(project, ProjectAchievement.values()[0].achievement, 4)
            //MyNotifier.notifyLevelUp(project, 2)
            //userTab.dailyCard.updateProgress(10, 0)
        }
        panel.add(button)
        return panel
    }

    companion object {
        lateinit var userTab: UserTab
        lateinit var achievementTab: AchievementsTab
    }
}