package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.enums.GlobalAchievement
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import java.awt.Font
import javax.swing.BorderFactory
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel

class ShowcaseCard(var id: Int) {
    val card = JPanel()
    private val iconLabel = JLabel()
    private val milestoneLabel = JLabel()

    init {
        card.layout = BoxLayout(card, BoxLayout.Y_AXIS)

        val innerCard = JPanel()
        innerCard.layout = BoxLayout(innerCard, BoxLayout.Y_AXIS)
        innerCard.border = BorderFactory.createEtchedBorder()

        setContent(id)

        innerCard.add(iconLabel)
        card.add(innerCard)
        card.add(milestoneLabel)
    }

    private fun setContent(id: Int) {
        milestoneLabel.font = Font(milestoneLabel.font.name, Font.PLAIN, 10)
        milestoneLabel.border = BorderFactory.createEmptyBorder(5, 0, 0, 0)
        if (id > 0) {
            val achEnum = GlobalAchievement.values()[id]
            iconLabel.toolTipText = achEnum.achievement.name
            iconLabel.icon = IconUtility().loadGlobalAchIcon(achEnum.achievement.iconPath)

            updateProgressShowcaseCard()
        } else {
            iconLabel.toolTipText = null
            milestoneLabel.text = null
            iconLabel.icon = IconUtility().loadEmptyIcon()
        }
    }

    fun updateProgressShowcaseCard() {
        val achEnum = GlobalAchievement.values()[id]
        val exp = ApplicationStatePersistence.getInstance().state.achievementList.first { it.id == id }.currentExp
        var i = 0
        while (i < achEnum.achievement.milestone.size - 1 && exp >= achEnum.achievement.milestone[i]) {
            i++
        }

        if (i == achEnum.achievement.milestone.size - 1 && exp >= achEnum.achievement.milestone[i]) {
            milestoneLabel.text = "Completed"
        } else {
            milestoneLabel.text = "$exp / ${achEnum.achievement.milestone[i]}"
        }
    }

    fun updateShowcaseCard(id: Int) {
        this.id = id
        setContent(id)
    }
}