package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.objects.*
import com.github.paolobd.intellijplugintemplate.services.ApplicationStatePersistence
import com.github.paolobd.intellijplugintemplate.services.ProjectStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.util.ui.JBUI
import java.awt.*
import javax.swing.*


class UserInterface(private val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()
    private var achievementPane = JBTabbedPane()

    init {
        //mainUI.addTab("Achievements", createAchievementsTab())
        mainUI.addTab("Profile", userTab.toolWindow)
        mainUI.addTab("Achievements", createAllAchievementsTab())
        //mainUI.addTab("Dev Tools", createDevTab())
    }

    fun getContent(): JBTabbedPane {
        return mainUI
    }

    private fun createDevTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val button = JButton("Reset values!")
        button.alignmentX = 0.5f // center button

        val text = JLabel("")
        text.alignmentX = 0.5f // center label

        button.addActionListener {
            ProjectStatePersistence.getInstance(project).resetState()
        }

        val button2 = JButton("Add GUI Library")
        button2.alignmentX = 0.5f

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

    private fun createAchievementsTab(achievements: MutableList<AchievementCard>, sort: Int): Component {
        val achievementContainer = JPanel()
        achievementContainer.layout = GridBagLayout()
        val constraint = GridBagConstraints()

        constraint.gridy = 0
        constraint.anchor = GridBagConstraints.EAST
        val items = arrayOf("Default", "A-Z", "Z-A", "Completion % up", "Completion % down")
        val dropdown = ComboBox(items)
        dropdown.selectedIndex = sort
        achievementContainer.add(dropdown, constraint)

        dropdown.addActionListener {
            when (dropdown.selectedIndex) {
                0 -> orderByDefault()
                1 -> orderByAlphabet(true)
                2 -> orderByAlphabet(false)
                3 -> orderByCompletionRate(true)
                4 -> orderByCompletionRate(false)
            }
        }

        constraint.gridy++
        constraint.anchor = GridBagConstraints.CENTER
        constraint.insets = JBUI.insets(3, 0)
        constraint.fill = GridBagConstraints.HORIZONTAL
        constraint.weightx = 1.0

        for (achievement in achievements) {
            achievementContainer.add(achievement.card, constraint)
            constraint.gridy++
        }

        constraint.weighty = 1.0
        achievementContainer.add(JLabel(), constraint)

        val scrollPane = JBScrollPane(achievementContainer)
        scrollPane.border = null

        return scrollPane
    }

    private fun createAllAchievementsTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val projectStateAchievements = ProjectStatePersistence.getInstance(project).state.achievementList
        val applicationStateAchievements = ApplicationStatePersistence.getInstance().state.globalAchievements

        for (achievementEnum in GlobalAchievement.values()) {
            val achievement = achievementEnum.achievement
            globalAchievementCards.add(
                AchievementCard(
                    achievementEnum.ordinal,
                    achievement.iconPath,
                    achievement.name,
                    achievement.description,
                    achievement.milestone,
                    achievement.userExperience,
                    projectStateAchievements.first { achievementEnum.ordinal == it.id }.currentExp
                )
            )
        }

        for (achievementEnum in ProjectAchievement.values()) {
            val achievement = achievementEnum.achievement
            projectAchievementCards.add(
                AchievementCard(
                    achievementEnum.ordinal,
                    achievement.iconPath,
                    achievement.name,
                    achievement.description,
                    achievement.milestone,
                    achievement.userExperience,
                    applicationStateAchievements.first { achievementEnum.ordinal == it.id }.currentExp
                )
            )
        }

        achievementPane = JBTabbedPane()

        achievementPane.addTab("Global", createAchievementsTab(globalAchievementCards, 0))
        achievementPane.addTab("Project", createAchievementsTab(projectAchievementCards, 0))

        toolWindowPanel.add(achievementPane)

        return toolWindowPanel
    }

    private fun createProfileTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)
        val wrapperPanel = JPanel(GridBagLayout())

        val leftPanel = JPanel(BorderLayout())

        val profilePicturePanel = JLabel(IconLoader.getIcon("/userInterface/user/user.svg", javaClass))
        profilePicturePanel.alignmentX = Component.CENTER_ALIGNMENT
        profilePicturePanel.alignmentY = Component.CENTER_ALIGNMENT

        leftPanel.add(profilePicturePanel, BorderLayout.CENTER)

        // Right Panel for User Information
        val rightPanel = JPanel(GridBagLayout())

        var gbc = GridBagConstraints()
        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.WEST
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = JBUI.insets(5)

        val usernameLabel = JLabel("Paolobd")
        usernameLabel.font = Font(usernameLabel.font.name, Font.BOLD, 16)
        rightPanel.add(usernameLabel, gbc)

        gbc.gridy++
        val userTitleLabel = JLabel("Always asleep")
        userTitleLabel.font = Font(userTitleLabel.font.name, Font.PLAIN, 14)
        rightPanel.add(userTitleLabel, gbc)

        gbc.gridy++
        val levelPanel = JPanel(BorderLayout())
        val progressBar = JProgressBar(0, 100)
        progressBar.value = 75 // Placeholder progress value

        val levelNumberLabel = JLabel("1") // Placeholder level number
            levelNumberLabel.border = BorderFactory.createEmptyBorder(0, 10, 0, 0)

        val levelExp = JLabel("Exp: 75 / 100")
        levelExp.alignmentX = Component.CENTER_ALIGNMENT

        levelPanel.add(progressBar, BorderLayout.WEST)
        levelPanel.add(levelNumberLabel, BorderLayout.EAST)
        levelPanel.add(levelExp, BorderLayout.SOUTH)
        rightPanel.add(levelPanel, gbc)

        // Achievement Squares Panel
        val showcasePanel = JPanel() // 1 row, 5 columns
        showcasePanel.layout = BoxLayout(showcasePanel, BoxLayout.Y_AXIS)
        val showcaseLabel = JLabel("Showcase")
        showcaseLabel.font = Font(showcaseLabel.font.name, Font.PLAIN, 14)
        showcaseLabel.alignmentX = Component.CENTER_ALIGNMENT
        showcaseLabel.border = BorderFactory.createEmptyBorder(0, 0, 10, 0)

        val achievementPanel = JPanel()
        achievementPanel.layout = BoxLayout(achievementPanel, BoxLayout.X_AXIS)
        achievementPanel.add(Box.createHorizontalGlue())

        // Create achievement squares (placeholders)
        for (i in 1..5) {
            val achievement = JPanel()
            achievement.border = BorderFactory.createEtchedBorder()
            val icon = JLabel(IconLoader.getIcon("/userInterface/GoldTrophy.svg", javaClass))
            achievement.add(icon)
            achievementPanel.add(achievement)
            achievementPanel.add(Box.createHorizontalGlue())
        }

        showcasePanel.add(showcaseLabel)
        showcasePanel.add(achievementPanel)

        val dailyPanel = JPanel()
        dailyPanel.layout = BoxLayout(dailyPanel, BoxLayout.Y_AXIS)
        val dailyLabel = JLabel("Daily Task")
        dailyLabel.font = Font(dailyLabel.font.name, Font.PLAIN, 14)
        dailyLabel.alignmentX = Component.CENTER_ALIGNMENT
        dailyLabel.border = BorderFactory.createEmptyBorder(20, 0, 10, 0)
        val achEnum = GlobalAchievement.NUM_CLICKS
        val ach = achEnum.achievement
        val dailyAchievement = AchievementCard(
            achEnum.ordinal, ach.iconPath, ach.name, ach.description, ach.milestone, ach.userExperience, 0
        )

        dailyPanel.add(dailyLabel)
        dailyPanel.add(dailyAchievement.card)

        // Add leftPanel and rightPanel to the main panel
        val mainPanel = JPanel()
        mainPanel.layout = BoxLayout(mainPanel, BoxLayout.X_AXIS)
        mainPanel.add(leftPanel)
        mainPanel.add(rightPanel)

        gbc = GridBagConstraints()
        gbc.anchor = GridBagConstraints.EAST
        gbc.gridy = 0

        val editIcon = JButton(IconLoader.getIcon("/userInterface/edit.svg", javaClass))

        editIcon.addActionListener {
            EditUserDialog().show()
        }

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

        toolWindowPanel.add(wrapperPanel)

        return toolWindowPanel
    }

    private fun orderByDefault() {
        projectAchievementCards.sortBy { it.id }
        globalAchievementCards.sortBy { it.id }

        val projectPane = createAchievementsTab(projectAchievementCards, 0)
        val applicationPane = createAchievementsTab(globalAchievementCards, 0)

        achievementPane.setComponentAt(0, applicationPane)
        achievementPane.setComponentAt(1, projectPane)
    }

    private fun orderByAlphabet(asc: Boolean) {
        when (asc) {
            true -> {
                projectAchievementCards.sortBy { it.titleLabel.text }
                globalAchievementCards.sortBy { it.titleLabel.text }
            }

            false -> {
                projectAchievementCards.sortByDescending { it.titleLabel.text }
                globalAchievementCards.sortByDescending { it.titleLabel.text }
            }
        }

        val selected = if (asc) 1 else 2

        val projectPane = createAchievementsTab(projectAchievementCards, selected)
        val applicationPane = createAchievementsTab(globalAchievementCards, selected)

        achievementPane.setComponentAt(0, applicationPane)
        achievementPane.setComponentAt(1, projectPane)
    }

    private fun orderByCompletionRate(asc: Boolean) {
        when (asc) {
            true -> {
                projectAchievementCards.sortBy { it.progressBar.value.toFloat() / it.progressBar.maximum }
                globalAchievementCards.sortBy { it.progressBar.value.toFloat() / it.progressBar.maximum }
            }

            false -> {
                projectAchievementCards.sortByDescending { it.progressBar.value.toFloat() / it.progressBar.maximum }
                globalAchievementCards.sortByDescending { it.progressBar.value.toFloat() / it.progressBar.maximum }
            }
        }

        val selected = if (asc) 3 else 4

        val projectPane = createAchievementsTab(projectAchievementCards, selected)
        val applicationPane = createAchievementsTab(globalAchievementCards, selected)

        achievementPane.setComponentAt(0, applicationPane)
        achievementPane.setComponentAt(1, projectPane)
    }

    companion object {
        var projectAchievementCards = mutableListOf<AchievementCard>()
        var globalAchievementCards = mutableListOf<AchievementCard>()
        var userTab = UserTab()
    }
}