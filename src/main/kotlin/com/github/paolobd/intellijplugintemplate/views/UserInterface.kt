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
import java.awt.event.ActionListener
import javax.swing.*


class UserInterface(private val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()
    private var achievementPane = JBTabbedPane()

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
            when(dropdown.selectedIndex) {
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

        for(achievement in achievements){
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

        for(achievement in ApplicationAchievementList.values()){
            globalAchievementCards.add(
                AchievementCard(
                    achievement.ordinal,
                    achievement.iconUrl,
                    achievement.title,
                    achievement.description,
                    achievement.total,
                    achievement.userExp,
                    applicationStateAchievements.first { achievement.ordinal == it.id }.currentExp
                )
            )
        }

        for(achievement in ProjectAchievementList.values()){
            projectAchievementCards.add(
                AchievementCard(
                    achievement.ordinal,
                    achievement.iconUrl,
                    achievement.title,
                    achievement.description,
                    achievement.total,
                    achievement.userExp,
                    projectStateAchievements.first { achievement.ordinal == it.id }.currentExp
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

        // Placeholder for profile picture (a circular panel)
        /*val profilePicturePanel: JPanel = object : JPanel() {
            override fun paintComponent(g: Graphics) {
                super.paintComponent(g)
                g.color = JBColor.BLUE // Placeholder color
                g.fillOval(0, 0, width, height)
            }
        }
        profilePicturePanel.preferredSize = Dimension(150, 150)*/

        val profilePicturePanel = JLabel(IconLoader.getIcon("/userInterface/user.svg", javaClass))
        //profilePicturePanel.preferredSize = Dimension(100, 100)


        val changePictureButton = JButton("Change Picture")
        leftPanel.add(profilePicturePanel, BorderLayout.CENTER)
        leftPanel.add(changePictureButton, BorderLayout.SOUTH)

        // Right Panel for User Information
        val rightPanel = JPanel(GridBagLayout())

        val gbc = GridBagConstraints()
        gbc.gridx = 0
        gbc.gridy = 0
        gbc.gridwidth = 1
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.insets = JBUI.insets(5)

        val usernameLabel = JTextField("Paolobd")
        usernameLabel.font = Font(usernameLabel.font.name, Font.BOLD, 14)
        rightPanel.add(usernameLabel, gbc)

        gbc.gridy++
        val userTitleLabel = JLabel("Always asleep")
        userTitleLabel.font = Font(userTitleLabel.font.name, Font.PLAIN, 12)
        rightPanel.add(userTitleLabel, gbc)

        gbc.gridy++
        val levelPanel = JPanel(BorderLayout())
        val progressBar = JProgressBar(0, 100)
        progressBar.value = 75 // Placeholder progress value

        val levelNumberLabel = JLabel("Level 5") // Placeholder level number

        levelPanel.add(progressBar, BorderLayout.NORTH)
        levelPanel.add(levelNumberLabel, BorderLayout.SOUTH)
        rightPanel.add(levelPanel, gbc)

        // Achievement Squares Panel

        // Achievement Squares Panel
        val achievementPanel = JPanel() // 1 row, 5 columns
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

            /*val achievementSquare = JPanel(BorderLayout())
            achievementSquare.border = BorderFactory.createLineBorder(JBColor.BLACK)
            val achievementIconLabel = JLabel("Achievement $i", SwingConstants.CENTER)
            achievementIconLabel.foreground = JBColor.BLUE // Placeholder color
            achievementSquare.add(achievementIconLabel, BorderLayout.CENTER)
            val achievementNameLabel = JLabel("Achievement Name $i", SwingConstants.CENTER)
            achievementSquare.add(achievementNameLabel, BorderLayout.SOUTH)
            achievementPanel.add(achievementSquare)*/
        }

        // Add leftPanel and rightPanel to the main panel
        val mainPanel = JPanel(BorderLayout())
        mainPanel.add(leftPanel, BorderLayout.WEST)
        mainPanel.add(rightPanel, BorderLayout.CENTER)

        val gbc2 = GridBagConstraints()
        gbc2.gridwidth = 1
        gbc2.gridx = 0
        gbc2.fill = GridBagConstraints.HORIZONTAL
        gbc2.gridy = 0
        gbc2.weightx = 1.0

        wrapperPanel.add(mainPanel, gbc2)
        gbc2.gridy = 1
        wrapperPanel.add(achievementPanel, gbc2)
        gbc2.gridy = 2
        gbc2.weighty = 1.0
        wrapperPanel.add(JLabel(), gbc2)



        toolWindowPanel.add(wrapperPanel)

        return toolWindowPanel
    }

    init {
        //mainUI.addTab("Achievements", createAchievementsTab())

        mainUI.addTab("Profile", createProfileTab())
        mainUI.addTab("Achievements", createAllAchievementsTab())
        mainUI.addTab("Dev Tools", createDevTab())
    }

    private fun orderByDefault(){
        projectAchievementCards.sortBy{ it.id }
        globalAchievementCards.sortBy{ it.id }

        val projectPane = createAchievementsTab(projectAchievementCards, 0)
        val applicationPane = createAchievementsTab(globalAchievementCards, 0)

        achievementPane.setComponentAt(0, applicationPane)
        achievementPane.setComponentAt(1, projectPane)
    }

    private fun orderByAlphabet(asc: Boolean){
        when(asc){
            true -> {
                projectAchievementCards.sortBy{ it.titleLabel.text }
                globalAchievementCards.sortBy{ it.titleLabel.text }
            }
            false -> {
                projectAchievementCards.sortByDescending{ it.titleLabel.text }
                globalAchievementCards.sortByDescending{ it.titleLabel.text }
            }
        }

        val selected = if (asc) 1 else 2

        val projectPane = createAchievementsTab(projectAchievementCards, selected)
        val applicationPane = createAchievementsTab(globalAchievementCards, selected)

        achievementPane.setComponentAt(0, applicationPane)
        achievementPane.setComponentAt(1, projectPane)
    }

    private fun orderByCompletionRate(asc: Boolean){
        when(asc){
            true -> {
                projectAchievementCards.sortBy{ it.progressBar.value.toFloat() / it.progressBar.maximum }
                globalAchievementCards.sortBy{ it.progressBar.value.toFloat() / it.progressBar.maximum }
            }
            false -> {
                projectAchievementCards.sortByDescending{ it.progressBar.value.toFloat() / it.progressBar.maximum }
                globalAchievementCards.sortByDescending{ it.progressBar.value.toFloat() / it.progressBar.maximum }
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
    }
}