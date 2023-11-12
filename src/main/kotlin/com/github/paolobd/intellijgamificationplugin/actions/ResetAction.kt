package com.github.paolobd.intellijgamificationplugin.actions

import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.DialogWrapper
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel

class ResetAction : AnAction("Reset Progress",
    "Reset your progress in the Game GUI plugin", null) {
    override fun actionPerformed(e: AnActionEvent) {
        ResetDialog().show()
    }
}

class ResetDialog : DialogWrapper(true) {

    init{
        title = "Reset Progress"
        setOKButtonText("Reset")
        init()
    }
    override fun createCenterPanel(): JComponent {
        val panel = JPanel()
        panel.add(JLabel("Are you sure? This operation cannot be undone"))
        return panel
    }

    override fun doOKAction() {
        ApplicationStatePersistence.getInstance().resetStateAndRefresh()
    }
}