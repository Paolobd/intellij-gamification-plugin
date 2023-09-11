package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.dataProviders.LevelDataProvider
import com.github.paolobd.intellijplugintemplate.dataProviders.TitleDataProvider
import com.github.paolobd.intellijplugintemplate.dataProviders.UserIconDataProvider
import com.github.paolobd.intellijplugintemplate.enums.GlobalAchievement
import com.github.paolobd.intellijplugintemplate.objects.*
import com.github.paolobd.intellijplugintemplate.services.ApplicationStatePersistence
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.util.ui.JBUI
import java.awt.*
import javax.swing.*

class UserTab {
    var toolWindow = JPanel()
    private var userIconLabel = JLabel()
    private var nameLabel = JLabel()
    private var titleLabel = JLabel()
    private var experienceBar = JProgressBar()
    private var experienceLabel = JLabel()
    private var levelLabel = JLabel()
    private var showcaseIcons = mutableListOf<JLabel>()

    init {
        createPanel()
    }

    private fun createPanel() {
        val userState = ApplicationStatePersistence.getInstance().state.userState

        //Left panel to show user profile picture
        val iconPanel = JPanel(BorderLayout())
        val userIcon = UserIconDataProvider().getUserIconById(userState.iconId)

        userIconLabel = JLabel(Icons().loadUserIcon(userIcon.fileName))
        userIconLabel.alignmentX = Component.CENTER_ALIGNMENT
        userIconLabel.alignmentY = Component.CENTER_ALIGNMENT
        iconPanel.add(userIconLabel)

        //Right panel to show username and title
        val infoPanel = JPanel(GridBagLayout())
        var gbc = GridBagConstraints()
        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.WEST
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = JBUI.insets(5)

        nameLabel = JLabel(userState.name)
        nameLabel.font = Font(nameLabel.font.name, Font.BOLD, 16)
        infoPanel.add(nameLabel, gbc)

        gbc.gridy++
        val title = TitleDataProvider().getTitleById(userState.titleId).text
        titleLabel = JLabel(title)
        titleLabel.font = Font(titleLabel.font.name, Font.PLAIN, 14)
        infoPanel.add(titleLabel, gbc)

        gbc.gridy++
        val levelPanel = JPanel(BorderLayout())
        val level = userState.level
        val experience = userState.experience
        val maxLevelExperience = LevelDataProvider().getLevelExperienceById(level)

        experienceBar = JProgressBar(0, maxLevelExperience)
        experienceBar.value = experience

        levelLabel = JLabel("$level")
        levelLabel.border = BorderFactory.createEmptyBorder(0, 10, 0, 0)

        experienceLabel = JLabel("$experience / $maxLevelExperience")
        experienceLabel.alignmentX = Component.CENTER_ALIGNMENT

        levelPanel.add(experienceBar, BorderLayout.WEST)
        levelPanel.add(levelLabel, BorderLayout.EAST)
        levelPanel.add(experienceLabel, BorderLayout.SOUTH)
        infoPanel.add(levelPanel, gbc)

        //Showcase Panel
        val showcasePanel = JPanel()
        showcasePanel.layout = BoxLayout(showcasePanel, BoxLayout.Y_AXIS)
        val showcaseLabel = JLabel("Showcase")
        showcaseLabel.font = Font(showcaseLabel.font.name, Font.PLAIN, 14)
        showcaseLabel.alignmentX = Component.CENTER_ALIGNMENT
        showcaseLabel.border = BorderFactory.createEmptyBorder(0, 0, 10, 0)

        val achievementPanel = JPanel()
        achievementPanel.layout = BoxLayout(achievementPanel, BoxLayout.X_AXIS)
        achievementPanel.add(Box.createHorizontalGlue())

        // Create achievement squares (placeholders)
        for (i in 0..4) {
            val achievement = JPanel()
            achievement.border = BorderFactory.createEtchedBorder()
            val iconId = userState.showcase[i]
            val achEnum = GlobalAchievement.values()[iconId]
            val icon = JLabel(Icons().loadGlobalAchIcon(achEnum.achievement.iconPath))
            achievement.add(icon)
            achievementPanel.add(achievement)
            achievementPanel.add(Box.createHorizontalGlue())

            showcaseIcons.add(icon)
        }

        showcasePanel.add(showcaseLabel)
        showcasePanel.add(achievementPanel)

        //Daily Achievement Panel
        val dailyPanel = JPanel()
        dailyPanel.layout = BoxLayout(dailyPanel, BoxLayout.Y_AXIS)
        val dailyLabel = JLabel("Daily Task")
        dailyLabel.font = Font(dailyLabel.font.name, Font.PLAIN, 14)
        dailyLabel.alignmentX = Component.CENTER_ALIGNMENT
        dailyLabel.border = BorderFactory.createEmptyBorder(20, 0, 10, 0)
        val achEnum = GlobalAchievement.NUM_CLICKS
        val ach = achEnum.achievement
        val dailyAchievement = AchievementCard(
            achEnum.ordinal, Icons().loadGlobalAchIcon(ach.iconPath), ach.name, ach.description, ach.milestone, ach.userExperience, 0
        )

        dailyPanel.add(dailyLabel)
        dailyPanel.add(dailyAchievement.card)

        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.X_AXIS)
        mainPanel.add(iconPanel)
        mainPanel.add(infoPanel)

        gbc = GridBagConstraints()
        gbc.anchor = GridBagConstraints.EAST
        gbc.gridy = 0

        val editIcon = JButton(Icons().loadEditIcon())

        editIcon.addActionListener {
            EditUserDialog().show()
        }

        toolWindow = SimpleToolWindowPanel(true, true)
        val wrapperPanel = JPanel(GridBagLayout())

        wrapperPanel.add(editIcon, gbc)

        gbc.anchor = GridBagConstraints.CENTER
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.gridy++
        gbc.weightx = 1.0
        wrapperPanel.add(mainPanel, gbc)

        gbc.gridy++
        wrapperPanel.add(Box.createVerticalStrut(20), gbc)

        gbc.gridy++
        wrapperPanel.add(showcasePanel, gbc)

        gbc.gridy++
        wrapperPanel.add(dailyPanel, gbc)

        gbc.gridy++
        gbc.weighty = 1.0
        wrapperPanel.add(JLabel(), gbc)

        toolWindow.add(JScrollPane(wrapperPanel))
    }

    fun updateUserInfo() {
        val userState = ApplicationStatePersistence.getInstance().state.userState

        val userIcon = UserIconDataProvider().getUserIconById(userState.iconId)
        userIconLabel.icon = Icons().loadUserIcon(userIcon.fileName)

        nameLabel.text = userState.name

        val title = TitleDataProvider().getTitleById(userState.titleId)
        titleLabel.text = title.text
    }

    fun updateUserExperience() {
        val userState = ApplicationStatePersistence.getInstance().state.userState

        val level = userState.level
        val maximum = LevelDataProvider().getLevelExperienceById(level)

        levelLabel.text = level.toString()

        experienceBar.value = userState.experience
        experienceBar.maximum = maximum

        experienceLabel.text = "${userState.experience} / $maximum"
    }

    fun updateUserShowcase() {
        val showcase = ApplicationStatePersistence.getInstance().state.userState.showcase

        for (i in 0..4) {
            val iconId = showcase[i]
            val iconEnum = GlobalAchievement.values()[iconId]

            showcaseIcons[i].icon = Icons().loadGlobalAchIcon(iconEnum.achievement.iconPath)
        }
    }
}