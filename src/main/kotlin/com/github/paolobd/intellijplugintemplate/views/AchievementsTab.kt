package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.enums.GlobalAchievement
import com.github.paolobd.intellijplugintemplate.enums.ProjectAchievement
import com.github.paolobd.intellijplugintemplate.enums.SortDropdown
import com.github.paolobd.intellijplugintemplate.objects.AchievementCard
import com.github.paolobd.intellijplugintemplate.services.ApplicationStatePersistence
import com.github.paolobd.intellijplugintemplate.services.ProjectStatePersistence
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.util.ui.JBUI
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import javax.swing.JLabel
import javax.swing.JPanel

class AchievementsTab(val project: Project) {
    var toolWindow = JPanel()
    var globalAchievementCards = mutableListOf<AchievementCard>()
    var projectAchievementCards = mutableListOf<AchievementCard>()
    private lateinit var achievementPane: JBTabbedPane

    init {
        createPanel()
    }

    fun updateProjectAchievement(id: Int, newExp: Int) {
        projectAchievementCards.first { it.id == id }.updateProgress(newExp)
    }

    fun updateGlobalAchievement(id: Int, newExp: Int) {
        globalAchievementCards.first { it.id == id }.updateProgress(newExp)
    }

    private fun createPanel() {
        toolWindow = SimpleToolWindowPanel(true, true)

        val projectStateAchievements = ProjectStatePersistence.getInstance(project).state.achievementList
        val applicationStateAchievements = ApplicationStatePersistence.getInstance().state.globalAchievements

        for (achievementEnum in GlobalAchievement.values()) {
            val achievement = achievementEnum.achievement
            globalAchievementCards.add(
                AchievementCard(
                    achievementEnum.ordinal,
                    Icons().loadGlobalAchIcon(achievement.iconPath),
                    achievement,
                    projectStateAchievements.first { achievementEnum.ordinal == it.id }.currentExp
                )
            )
        }

        for (achievementEnum in ProjectAchievement.values()) {
            val achievement = achievementEnum.achievement
            projectAchievementCards.add(
                AchievementCard(
                    achievementEnum.ordinal,
                    Icons().loadProjectAchIcon(achievement.iconPath),
                    achievement,
                    applicationStateAchievements.first { achievementEnum.ordinal == it.id }.currentExp
                )
            )
        }

        achievementPane = JBTabbedPane()
        achievementPane.add("Global", createTab(globalAchievementCards, SortDropdown.DEFAULT))
        achievementPane.add("Project", createTab(projectAchievementCards, SortDropdown.DEFAULT))
        toolWindow.add(achievementPane)
    }

    private fun createTab(achievements: List<AchievementCard>, sortEnum: SortDropdown): JBScrollPane {
        val achievementContainer = JPanel(GridBagLayout())
        val gbc = GridBagConstraints()

        gbc.gridy = 0
        gbc.anchor = GridBagConstraints.EAST
        achievementContainer.add(createDropdown(sortEnum), gbc)

        gbc.gridy++
        gbc.anchor = GridBagConstraints.CENTER
        gbc.insets = JBUI.insets(3, 0)
        gbc.fill = GridBagConstraints.HORIZONTAL
        gbc.weightx = 1.0

        for (achievement in achievements) {
            achievementContainer.add(achievement.card, gbc)
            gbc.gridy++
        }

        gbc.weighty = 1.0
        achievementContainer.add(JLabel(), gbc)

        val scrollPane = JBScrollPane(achievementContainer)
        scrollPane.border = null

        return scrollPane
    }

    private fun createDropdown(sortEnum: SortDropdown): ComboBox<String> {
        val items = SortDropdown.values().map { it.text }.toTypedArray()
        val dropdown = ComboBox(items)
        dropdown.selectedIndex = sortEnum.ordinal

        dropdown.addActionListener {
            when (SortDropdown.values()[dropdown.selectedIndex]) {
                SortDropdown.DEFAULT -> orderByDefault()
                SortDropdown.ALPHABETIC_ASC -> orderByAlphabet(SortDropdown.ALPHABETIC_ASC)
                SortDropdown.ALPHABETIC_DSC -> orderByAlphabet(SortDropdown.ALPHABETIC_DSC)
                SortDropdown.COMPLETION_ASC -> orderByCompletionRate(SortDropdown.COMPLETION_ASC)
                SortDropdown.COMPLETION_DSC -> orderByCompletionRate(SortDropdown.COMPLETION_DSC)
            }
        }

        return dropdown
    }

    private fun orderByDefault() {
        projectAchievementCards.sortBy { it.id }
        globalAchievementCards.sortBy { it.id }

        substituteAchievementPane(SortDropdown.DEFAULT)
    }

    private fun orderByAlphabet(sortEnum: SortDropdown) {
        when (sortEnum) {
            SortDropdown.ALPHABETIC_ASC -> {
                projectAchievementCards.sortBy { it.titleLabel.text }
                globalAchievementCards.sortBy { it.titleLabel.text }
            }

            SortDropdown.ALPHABETIC_DSC -> {
                projectAchievementCards.sortByDescending { it.titleLabel.text }
                globalAchievementCards.sortByDescending { it.titleLabel.text }
            }

            else -> return
        }

        substituteAchievementPane(sortEnum)
    }

    private fun orderByCompletionRate(sortEnum: SortDropdown) {
        when (sortEnum) {
            SortDropdown.COMPLETION_ASC -> {
                projectAchievementCards.sortBy { it.progressBar.value.toFloat() / it.progressBar.maximum }
                globalAchievementCards.sortBy { it.progressBar.value.toFloat() / it.progressBar.maximum }
            }

            SortDropdown.COMPLETION_DSC -> {
                projectAchievementCards.sortByDescending { it.progressBar.value.toFloat() / it.progressBar.maximum }
                globalAchievementCards.sortByDescending { it.progressBar.value.toFloat() / it.progressBar.maximum }
            }

            else -> return
        }

        substituteAchievementPane(sortEnum)
    }

    private fun substituteAchievementPane(sortEnum: SortDropdown) {
        val projectPane = createTab(projectAchievementCards, sortEnum)
        val applicationPane = createTab(globalAchievementCards, sortEnum)

        achievementPane.setComponentAt(0, applicationPane)
        achievementPane.setComponentAt(1, projectPane)
    }
}