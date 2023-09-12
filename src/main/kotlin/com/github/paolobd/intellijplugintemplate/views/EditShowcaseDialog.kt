package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.enums.GlobalAchievement
import com.github.paolobd.intellijplugintemplate.services.ApplicationStatePersistence
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBScrollPane
import java.awt.*
import javax.swing.*

class EditShowcaseDialog : DialogWrapper(true) {
    private var selectedAchievements = mutableMapOf<Int, JButton>()

    init {
        title = "Edit Showcase"
        setOKButtonText("Save")
        init()
    }

    override fun createCenterPanel(): JComponent {
        val showcase = ApplicationStatePersistence.getInstance().state.userState.showcase

        val panel = JPanel(GridBagLayout())
        val gbc = GridBagConstraints()
        gbc.gridy = 0

        val informativeLabel = JLabel(
            "<html>You can select up to 5 global achievements you have unlocked<br> " +
                    "to show off in your profile</html>"
        )
        informativeLabel.font = Font(informativeLabel.font.name, Font.PLAIN, 14)
        informativeLabel.alignmentX = Component.CENTER_ALIGNMENT
        panel.add(informativeLabel, gbc)

        gbc.gridy++
        panel.add(Box.createVerticalStrut(20), gbc)

        val showcasePanel = JPanel(GridLayout(0, 5))

        var elements = 0
        val achievementStats = ApplicationStatePersistence.getInstance().state.globalAchievements
        for (achEnum in GlobalAchievement.values()) {
            val currentExp = achievementStats.find { it.id == achEnum.ordinal }!!.currentExp

            if (currentExp >= achEnum.achievement.milestone) {
                elements++
                val icon = Icons().loadGlobalAchIcon(achEnum.achievement.iconPath)
                val iconButton = JButton(icon)
                iconButton.toolTipText = achEnum.achievement.name
                iconButton.isRequestFocusEnabled = false

                val emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5)
                val lineBorder = BorderFactory.createLineBorder(JBColor.BLACK, 2)
                val selectedBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder)
                val notSelectedBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7)


                iconButton.border = notSelectedBorder

                if (showcase.contains(achEnum.ordinal)) {
                    iconButton.border = selectedBorder

                    selectedAchievements[achEnum.ordinal] = iconButton
                }

                showcasePanel.add(iconButton)

                iconButton.addActionListener {
                    val ach = selectedAchievements[achEnum.ordinal]

                    if (ach != null) {
                        ach.border = notSelectedBorder
                        selectedAchievements.remove(achEnum.ordinal)
                    } else {
                        if (selectedAchievements.size < 5) {
                            iconButton.border = selectedBorder
                            selectedAchievements[achEnum.ordinal] = iconButton
                        }
                    }
                }

            }
        }

        gbc.gridy++
        if (elements != 0) {
            panel.add(JBScrollPane(showcasePanel), gbc)
        }
        else {
            val label = JLabel("Oops! You need to unlock at least one achievement")
            label.font = Font(label.font.name, Font.PLAIN, 14)
            panel.add(label, gbc)
        }

        gbc.gridy++
        gbc.weighty = 1.0
        panel.add(JLabel(), gbc)

        return panel
    }

    override fun doOKAction() {
        val selected = selectedAchievements.keys.toMutableList()

        while(selected.size < 5){
            selected.add(-1)
        }

        ApplicationStatePersistence.getInstance().changeShowcase(selected)
        super.doOKAction()
    }
}