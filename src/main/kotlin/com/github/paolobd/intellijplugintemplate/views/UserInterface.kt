package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.objects.AchievementList
import com.github.paolobd.intellijplugintemplate.objects.AchievementUI
import com.github.paolobd.intellijplugintemplate.services.MyStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.util.ui.JBUI
import java.awt.Component
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.*


class UserInterface(private val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()

    fun getContent(): JBTabbedPane {
        return mainUI
    }

    private fun createProfileTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val button = JButton("Reset values!")
        button.alignmentX = 0.5f // center button

        val text = JLabel("")
        text.alignmentX = 0.5f // center label

        button.addActionListener {
            MyStatePersistence.getInstance(project).resetState()
        }

        val button2 = JButton("Add GUI Library")
        button2.alignmentX = 0.5f;

        //panel.add(Box.createVerticalGlue()); // space above button
        panel.add(button)
        panel.add(button2)
        panel.add(Box.createVerticalStrut(10)) // space below button
        //panel.add(text)
        //panel.add(Box.createVerticalGlue()) // space below label
        //panel.add(createFileButton)

        toolWindowPanel.add(panel)

        return toolWindowPanel
    }

    private fun createAchievementsTab(): Component {
        val numColumn = 5
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val panel = JPanel()

        panel.layout = GridBagLayout()
        val con = GridBagConstraints()
        con.gridx = 0
        con.gridy = 0

        //Title of the achievements page that takes all the 4 columns
        con.gridwidth = numColumn
        panel.add(JLabel("Your Project Achievements List"), con)

        //We only occupy one column each time now and pass on next row
        con.gridwidth = 1
        con.gridy++

        //Border between elements in the grid
        con.insets = JBUI.insets(5)

        val stateAchievements = MyStatePersistence.getInstance(project).state.achievementList

        for (achievement in AchievementList.values()) {

            //achievement contains achievement Name, Description, MaxExp, IconName

            val showAchievementIcon = JLabel(IconLoader.getIcon(achievement.iconName, javaClass))
            val borderAchievementIcon = BorderFactory.createLineBorder(JBColor.BLACK, 1)
            val emptyBorder = JBUI.Borders.empty(2)
            showAchievementIcon.border = BorderFactory.createCompoundBorder(borderAchievementIcon, emptyBorder)

            val titleLabel = JLabel(achievement.achievementName)

            //The problem is I cannot resize the svg if I load it directly from AllIcons
            //val tooltipIcon = JLabel(AllIcons.General.QuestionDialog)
            //This svg is resized in userInterface folder
            val tooltipIcon = JLabel(IconLoader.getIcon("userInterface/Question-icon.svg", javaClass))
            tooltipIcon.toolTipText = achievement.achievementDescription

            //val exp = stateAchievements.getOrPut(achievement.ordinal) {0}
            val exp = stateAchievements.first{ achievement.ordinal == it.id }.currentExp

            val progressBar = JProgressBar(0, achievement.maxExp)
            progressBar.value = exp

            val progressLabel = JLabel("$exp / ${achievement.maxExp}")

            AchievementUI.getList().add(
                AchievementUI(
                    achievement.ordinal, progressBar, progressLabel
                )
            )

            //Inserting the achievement icon
            con.gridx = 0
            con.fill = GridBagConstraints.NONE
            con.weightx = 0.05
            panel.add(showAchievementIcon, con)

            con.gridx++
            con.fill = GridBagConstraints.NONE
            con.weightx = 0.30
            panel.add(titleLabel, con)

            con.gridx++
            con.fill = GridBagConstraints.NONE
            con.weightx = 0.05
            panel.add(tooltipIcon, con)

            con.gridx++
            con.fill = GridBagConstraints.HORIZONTAL
            con.weightx = 0.50
            panel.add(progressBar, con)

            con.gridx++
            con.fill = GridBagConstraints.NONE
            con.weightx = 0.10
            panel.add(progressLabel, con)

            //Go to next row
            con.gridy++
        }

        con.gridx = 1
        con.gridwidth = 3
        con.weightx = 0.0

        con.gridy++

        //Insert empty element so that the others are pushed to the top
        con.gridx = 0
        con.gridwidth = numColumn
        con.weightx = 0.0
        con.weighty = 1.0
        panel.add(JLabel(), con)

        val scroll = JBScrollPane(panel)
        toolWindowPanel.add(scroll)
        return toolWindowPanel
    }

    private fun createProvaTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val achievementContainer = JPanel()
        achievementContainer.layout = GridBagLayout()
        val constraint = GridBagConstraints()

        constraint.insets = JBUI.insets(3, 0)
        constraint.gridy = 0
        constraint.fill = GridBagConstraints.HORIZONTAL
        constraint.weightx = 1.0

        val stateAchievements = MyStatePersistence.getInstance(project).state.achievementList

        // Create achievement cards
        for (achievement in AchievementList.values()) {
            val achievementCard: JPanel = AchievementUI.createAchievementCard(
                achievement.ordinal,
                achievement.iconName,
                achievement.achievementName,
                achievement.achievementDescription,
                achievement.maxExp,
                stateAchievements.first{ achievement.ordinal == it.id }.currentExp)

            achievementContainer.add(achievementCard, constraint)
            constraint.gridy ++
        }
        constraint.weighty = 1.0
        achievementContainer.add(JLabel(), constraint)

        val scrollPane = JBScrollPane(achievementContainer)
        toolWindowPanel.setContent(scrollPane)

        return toolWindowPanel
    }

    /*private fun createAchievementCard(id: Int, iconName: String, achievementName: String,
                                      achievementDescription: String, maxExp: Int): JPanel{
        val card = JPanel()
        card.border = BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 5)
        )
        card.layout = BorderLayout()

        // Left panel for the achievement icon (square)
        val iconPanel = JPanel()
        val icon = JLabel(IconLoader.getIcon(iconName, javaClass))

        iconPanel.add(icon)

        // Center panel for title and description
        val centerPanel = JPanel()
        centerPanel.layout = BoxLayout(centerPanel, BoxLayout.Y_AXIS)

        val titleLabel = JLabel(achievementName)
        titleLabel.font = Font(titleLabel.font.name, Font.BOLD, 14)
        val descriptionLabel = JLabel(achievementDescription)
        descriptionLabel.font = Font(descriptionLabel.font.name, Font.PLAIN, 12)

        centerPanel.add(Box.createVerticalGlue())
        centerPanel.add(titleLabel)
        centerPanel.add(descriptionLabel)
        centerPanel.add(Box.createVerticalGlue())

        // Right panel for progress bar, progress label and xp
        val rightPanel = JPanel()
        rightPanel.layout = BoxLayout(rightPanel, BoxLayout.Y_AXIS)

        val progressBar = JProgressBar(0, maxExp)
        val exp = MyStatePersistence.getInstance(project).state.achievementList.first{ id == it.id }.currentExp
        progressBar.value = exp

        val progressLabel = JLabel("$exp / $maxExp") // Placeholder for progress label
        progressLabel.font = Font(progressLabel.font.name, Font.PLAIN, 12)
        progressLabel.alignmentX = JLabel.CENTER_ALIGNMENT

        // Bottom right panel for XP value
        val xpLabel = JLabel("XP: 10")
        xpLabel.font = Font(xpLabel.font.name, Font.ITALIC, 11)
        xpLabel.alignmentX = Component.CENTER_ALIGNMENT

        rightPanel.add(Box.createVerticalGlue())
        rightPanel.add(progressBar)
        rightPanel.add(progressLabel)
        rightPanel.add(xpLabel)

        // Add components to the card
        card.add(iconPanel, BorderLayout.WEST)
        card.add(centerPanel, BorderLayout.CENTER)
        card.add(rightPanel, BorderLayout.EAST)

        return card
    }*/

    init {
        //mainUI.addTab("Achievements", createAchievementsTab())
        mainUI.addTab("Achievements", createProvaTab())
        mainUI.addTab("Dev Tools", createProfileTab())
    }

}