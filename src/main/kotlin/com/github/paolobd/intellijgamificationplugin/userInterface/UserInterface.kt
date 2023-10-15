package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.enums.ProjectAchievement
import com.github.paolobd.intellijgamificationplugin.services.ProjectStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.ui.components.JBTabbedPane
import javax.swing.JButton
import javax.swing.JPanel


class UserInterface(private val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()

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

    fun debugButton(): JPanel {
        val panel = JPanel()
        val button = JButton("Click me!")

        button.addActionListener{
            ProjectStatePersistence.getInstance(project).addExp(project, ProjectAchievement.values()[0].achievement, 4)
            //MyNotifier.notifyLevelUp(project, 2)
        }
        panel.add(button)
        return panel
    }

    companion object {
        lateinit var userTab: UserTab
        lateinit var achievementTab: AchievementsTab
    }
}