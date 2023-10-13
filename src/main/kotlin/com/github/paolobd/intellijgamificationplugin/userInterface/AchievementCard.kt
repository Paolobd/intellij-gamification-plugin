package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataClasses.Achievement
import com.intellij.ui.JBColor
import java.awt.BorderLayout
import java.awt.Component
import java.awt.Font
import javax.swing.*

class AchievementCard(
    val achievement: Achievement, iconSvg: Icon, currentExp: Int
) {
    val card: JPanel = JPanel()
    private var icon: JLabel
    private var titleLabel: JLabel
    private var descriptionLabel: JLabel
    var progressBar: JProgressBar
    private var milestoneLabel: JLabel
    private var progressLabel: JLabel
    private var xpLabel: JLabel
    private var currentMilestone = 0

    init {
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
        //centerPanel.layout = FlowLayout()

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

        while (currentMilestone < achievement.milestone.size-1 && currentExp >= achievement.milestone[currentMilestone]) {
            currentMilestone++
        }

        milestoneLabel = JLabel()
        milestoneLabel.font = Font(milestoneLabel.font.name, Font.ITALIC, 10)
        milestoneLabel.alignmentX = JLabel.CENTER_ALIGNMENT

        if (achievement.milestone.size > 1) {
            milestoneLabel.text = "Milestone #${currentMilestone + 1}/${achievement.milestone.size}"
        }

        progressBar = JProgressBar(0, achievement.milestone[currentMilestone])
        progressBar.value = currentExp

        progressLabel = JLabel("$currentExp / ${achievement.milestone[currentMilestone]}") // Placeholder for progress label
        progressLabel.font = Font(progressLabel.font.name, Font.PLAIN, 12)
        progressLabel.alignmentX = JLabel.CENTER_ALIGNMENT

        // Bottom right panel for XP value
        xpLabel = JLabel("Gives ${achievement.userExperience[currentMilestone]} xp")
        xpLabel.font = Font(xpLabel.font.name, Font.ITALIC, 10)
        xpLabel.alignmentX = JLabel.CENTER_ALIGNMENT

        rightPanel.add(milestoneLabel)
        rightPanel.add(Box.createVerticalGlue())
        rightPanel.add(progressBar)
        rightPanel.add(progressLabel)
        rightPanel.add(Box.createVerticalGlue())
        rightPanel.add(xpLabel)

        // Add components to the card
        card.add(iconPanel, BorderLayout.WEST)
        card.add(centerPanel, BorderLayout.CENTER)
        card.add(rightPanel, BorderLayout.EAST)
    }

    fun updateProgress(exp: Int, milestoneIndex: Int) {
        if(milestoneIndex != currentMilestone){
            currentMilestone = milestoneIndex
            milestoneLabel.text = "Milestone #${currentMilestone + 1}/${achievement.milestone.size}"
            xpLabel.text = "Gives ${achievement.userExperience[currentMilestone]} xp"
            progressBar.maximum = achievement.milestone[milestoneIndex]
        }

        progressBar.value = exp
        progressLabel.text = "$exp / ${progressBar.maximum}"
    }
}