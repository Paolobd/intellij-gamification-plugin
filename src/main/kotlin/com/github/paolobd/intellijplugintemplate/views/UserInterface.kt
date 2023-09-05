package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.objects.AchievementUI
import com.github.paolobd.intellijplugintemplate.objects.ProjectAchievementList
import com.github.paolobd.intellijplugintemplate.services.ProjectStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.util.ui.JBUI
import java.awt.*
import javax.swing.*


class UserInterface(private val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()

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

    /*private fun createAchievementsTab(): Component {
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
    }*/

    private fun createProvaTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val tabbedPane = JBTabbedPane()

        val achievementContainer = JPanel()
        achievementContainer.layout = GridBagLayout()
        val constraint = GridBagConstraints()

        constraint.insets = JBUI.insets(3, 0)
        constraint.gridy = 0
        constraint.fill = GridBagConstraints.HORIZONTAL
        constraint.weightx = 1.0

        val stateAchievements = ProjectStatePersistence.getInstance(project).state.achievementList

        // Create achievement cards
        for (achievement in ProjectAchievementList.values()) {
            val achievementCard: JPanel = AchievementUI.createAchievementCard(
                achievement.ordinal,
                achievement.iconUrl,
                achievement.title,
                achievement.description,
                achievement.total,
                achievement.userExp,
                stateAchievements.first{ achievement.ordinal == it.id }.currentExp)

            achievementContainer.add(achievementCard, constraint)
            constraint.gridy ++
        }
        constraint.weighty = 1.0
        achievementContainer.add(JLabel(), constraint)

        val scrollPane = JBScrollPane(achievementContainer)
        //toolWindowPanel.setContent(scrollPane)
        toolWindowPanel.add(tabbedPane)

        tabbedPane.addTab("Project", achievementContainer)
        tabbedPane.addTab("Global", JLabel("Oh you touch my tralala"))

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
        mainUI.addTab("Achievements", createProvaTab())
        mainUI.addTab("Dev Tools", createDevTab())
    }

}