package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import java.awt.Font
import javax.swing.*

class ShowcaseCard(var id: Int) {
    val card = JPanel()
    private val iconLabel = JLabel()
    private val milestoneLabel = JLabel()

    init {
        card.layout = BoxLayout(card, BoxLayout.Y_AXIS)

        setContent(id)

        val innerCard = JPanel()
        innerCard.border = BorderFactory.createEtchedBorder()
        innerCard.add(iconLabel)

        val milestoneCard = JPanel()
        milestoneCard.layout = BoxLayout(milestoneCard, BoxLayout.X_AXIS)
        milestoneCard.add(Box.createHorizontalGlue())
        milestoneCard.add(milestoneLabel)
        milestoneCard.add(Box.createHorizontalGlue())

        card.add(innerCard)
        card.add(milestoneCard)
    }

    private fun setContent(id: Int) {
        iconLabel.border = BorderFactory.createEmptyBorder(5, 5, 5, 5)

        milestoneLabel.border = BorderFactory.createEmptyBorder(5, 0, 0, 0)

        if (id > 0) {
            val achievement = GlobalAchievement.values().map { it.achievement }.first { it.id == id }
            iconLabel.toolTipText = achievement.name
            iconLabel.icon = IconUtility().loadGlobalAchIcon(achievement.iconPath)

            milestoneLabel.font = Font(milestoneLabel.font.name, Font.PLAIN, 10)

            updateProgressShowcaseCard()
        } else {
            iconLabel.toolTipText = null

            milestoneLabel.font = Font(milestoneLabel.font.name, Font.ITALIC, 10)
            milestoneLabel.text = "Empty"
            iconLabel.icon = IconUtility().loadEmptyIcon()
        }
    }

    fun updateProgressShowcaseCard() {
        if (id <= 0) return

        val achievement = GlobalAchievement.values().map { it.achievement }.first { it.id == id }
        val exp = ApplicationStatePersistence.getInstance().state.achievementList.first { it.id == id }.currentExp
        var i = 0
        while (i < achievement.milestone.size - 1 && exp >= achievement.milestone[i]) {
            i++
        }

        if (i == achievement.milestone.size - 1 && exp >= achievement.milestone[i]) {
            milestoneLabel.text = "Completed"
        } else {
            milestoneLabel.text = "$exp / ${achievement.milestone[i]}"
        }
    }

    fun updateShowcaseCard(id: Int) {
        this.id = id
        setContent(id)
    }
}