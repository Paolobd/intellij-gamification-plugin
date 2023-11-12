package com.github.paolobd.intellijgamificationplugin.listeners

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.openapi.wm.ToolWindowManager
import javax.swing.SwingUtilities

class StartActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        SwingUtilities.invokeLater {
            val toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Game GUI")

            //If the toolWindow is not visible it gets created here, or the data logic/update interface logic
            //could create some problems because it does not find the user interface classes
            if (toolWindow != null && !toolWindow.isVisible){
                toolWindow.activate(null)
            }
        }
    }
}