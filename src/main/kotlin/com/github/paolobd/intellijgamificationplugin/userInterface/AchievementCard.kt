package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Font
import javax.swing.*

class AchievementCard(
    val id: Int, iconSvg: Icon, achievement: Achievement, current: Int
) {
    val card: JPanel = JPanel()
    private var icon: JLabel
    var titleLabel: JLabel
    private var descriptionLabel: JLabel
    var progressBar: JProgressBar
    private var progressLabel: JLabel
    private var xpLabel: JLabel

    init{
        card.border = BorderFactory.createCompoundBorder(
            BorderFactory.createEtchedBorder(),
            BorderFactory.createEmptyBorder(3, 3, 3, 5)
        )
        card.layout = BorderLayout()

        // Left panel for the achievement icon (square)
        val iconPanel = JPanel()
        icon = JLabel(iconSvg)

        iconPanel.add(icon)

        // Center panel for title and description
        val centerPanel = JPanel()
        centerPanel.layout = BoxLayout(centerPanel, BoxLayout.Y_AXIS)

        titleLabel = JLabel(achievement.name)
        titleLabel.font = Font(titleLabel.font.name, Font.BOLD, 14)
        descriptionLabel = JLabel("<html>${achievement.description}</html>")
        descriptionLabel.font = Font(descriptionLabel.font.name, Font.PLAIN, 12)

        centerPanel.add(Box.createVerticalGlue())
        centerPanel.add(titleLabel)
        centerPanel.add(descriptionLabel)
        centerPanel.add(Box.createVerticalGlue())

        // Right panel for progress bar, progress label and xp
        val rightPanel = JPanel()
        rightPanel.layout = BoxLayout(rightPanel, BoxLayout.Y_AXIS)

        progressBar = JProgressBar(0, achievement.milestone)
        progressBar.value = current

        progressLabel = JLabel("$current / ${achievement.milestone}") // Placeholder for progress label
        progressLabel.font = Font(progressLabel.font.name, Font.PLAIN, 12)
        progressLabel.alignmentX = JLabel.CENTER_ALIGNMENT

        // Bottom right panel for XP value
        xpLabel = JLabel("Gives ${achievement.userExperience} xp")
        xpLabel.font = Font(xpLabel.font.name, Font.ITALIC, 10)
        xpLabel.alignmentX = Component.CENTER_ALIGNMENT

        rightPanel.add(Box.createVerticalGlue())
        rightPanel.add(progressBar)
        rightPanel.add(progressLabel)
        rightPanel.add(xpLabel)

        // Add components to the card
        card.add(iconPanel, BorderLayout.WEST)
        card.add(centerPanel, BorderLayout.CENTER)
        card.add(rightPanel, BorderLayout.EAST)
    }

    fun updateProgress(newExp: Int) {
        progressBar.value = newExp
        progressLabel.text = "$newExp / ${progressBar.maximum}"
    }
}