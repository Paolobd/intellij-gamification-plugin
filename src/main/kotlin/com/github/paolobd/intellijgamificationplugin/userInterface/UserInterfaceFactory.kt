package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.userInterface.UserInterface
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory


class UserInterfaceFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        //ApplicationStatePersistence.getInstance().resetState()
        val userInterface = UserInterface(project)
        val contentManager = toolWindow.contentManager
        val content = contentManager.factory.createContent(userInterface.getContent(), null, false)
        toolWindow.contentManager.addContent(content)
    }
}
