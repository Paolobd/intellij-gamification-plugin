package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.actions.ResetAction
import com.github.paolobd.intellijgamificationplugin.services.AchievementService
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.github.paolobd.intellijgamificationplugin.services.ProjectStatePersistence
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory


class UserInterfaceFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {

        //Used for debug purposes to reset the state each time
        //ApplicationStatePersistence.getInstance().resetState()

        val applicationState = ApplicationStatePersistence.getInstance()
        val projectState = ProjectStatePersistence.getInstance(project)

        applicationState.addMissingAndCheckDaily()
        projectState.addMissing(applicationState.state.timestamp)

        AchievementService.project = project

        val group = DefaultActionGroup()
        group.add(ResetAction())

        toolWindow.setAdditionalGearActions(group)

        val userInterface = UserInterface(project)
        val contentManager = toolWindow.contentManager
        val content = contentManager.factory.createContent(userInterface.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }
}
