package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.enums.GlobalAchievement
import com.github.paolobd.intellijplugintemplate.enums.ProjectAchievement
import com.github.paolobd.intellijplugintemplate.objects.*
import com.github.paolobd.intellijplugintemplate.services.ApplicationStatePersistence
import com.github.paolobd.intellijplugintemplate.services.ProjectStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.util.ui.JBUI
import java.awt.*
import javax.swing.*


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