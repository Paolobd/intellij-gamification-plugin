package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.objects.Achievement
import com.github.paolobd.intellijplugintemplate.objects.AchievementList
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.SimpleToolWindowPanel
import com.intellij.openapi.util.IconLoader
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.PsiManager
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.components.JBTabbedPane
import com.intellij.util.ui.JBUI
import java.awt.*
import javax.swing.*
import com.intellij.lang.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.roots.ModuleRootModel
import com.intellij.openapi.roots.ModuleRootModificationUtil
import com.intellij.openapi.roots.OrderRootType
import com.intellij.openapi.roots.libraries.LibraryTablesRegistrar
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiDocumentManager
import java.io.File
import java.net.URLClassLoader

class UserInterface(val project: Project) {
    private var mainUI: JBTabbedPane = JBTabbedPane()
    //val achievementList = mutableListOf<Achievement>()
    private val achievementList = AchievementList().getList()

    fun getContent(): JBTabbedPane {
        return mainUI
    }

    private fun createProfileTab(): Component {
        val toolWindowPanel = SimpleToolWindowPanel(true, true)

        val panel = JPanel()
        panel.layout = BoxLayout(panel, BoxLayout.Y_AXIS)

        val button = JButton("Click me!")
        button.alignmentX = 0.5f // center button

        val text = JLabel("")
        text.alignmentX = 0.5f // center label

        button.addActionListener {
            text.text = "You've clicked me!"
        }

        //val createFileButton = JButton("Add library")

/*        createFileButton.addActionListener{
            val projectLibraryTable = LibraryTablesRegistrar.getInstance().getLibraryTable(project)
            val projectLibraryModel = projectLibraryTable.modifiableModel

            val library = projectLibraryModel.createLibrary("prova-0.0.1")
            val libraryModel = library.modifiableModel
            val pathUrl = VirtualFileManager.constructUrl(LocalFileSystem.PROTOCOL, "/Users/prova-0.0.1")
            val file = VirtualFileManager.getInstance().findFileByUrl(pathUrl)

            if(file != null) {
                libraryModel.addRoot(file, OrderRootType.CLASSES)

                ApplicationManager.getApplication().runWriteAction {
                    libraryModel.commit()
                    projectLibraryModel.commit()
                }
            }

        }*/

        //panel.add(Box.createVerticalGlue()); // space above button
        panel.add(button)
        panel.add(Box.createVerticalStrut(10)) // space below button
        panel.add(text)
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
        panel.add(JLabel("Questa é la lista dei tuoi achievements"), con)

        //We only occupy one column each time now and pass on next row
        con.gridwidth = 1
        con.gridy++

        //Border between elements in the grid
        con.insets = JBUI.insets(5)

        for(achievement in achievementList){
            val showAchievementIcon = JLabel(achievement.icon)
            val borderAchievementIcon = BorderFactory.createLineBorder(JBColor.BLACK, 1)
            val emptyBorder = JBUI.Borders.empty(2)
            showAchievementIcon.border = BorderFactory.createCompoundBorder(borderAchievementIcon, emptyBorder)

            val titleLabel = JLabel(achievement.title)

            //The problem is I cannot resize the svg if I load it directly from AllIcons
            //val tooltipIcon = JLabel(AllIcons.General.QuestionDialog)
            //This svg is resized in userInterface folder
            val tooltipIcon = JLabel(IconLoader.getIcon("userInterface/Question-icon.svg", javaClass))
            tooltipIcon.toolTipText = achievement.description

            val progressBar = JProgressBar(0, achievement.maxExp)
            progressBar.value = achievement.currentExp

            val progressLabel = JLabel("${achievement.currentExp} / ${achievement.maxExp}")

            achievement.progressBar = progressBar
            achievement.progressLabel = progressLabel

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
        /*var button = JButton("Increase 10 exp achievement 1")

        button.addActionListener{
            achievementList.first { it.id == 1 }.addExperience(10)
        }
        panel.add(button, con)

        con.gridy++

        button = JButton("Increase 50 exp achievement 3")

        button.addActionListener{
            achievementList.first { it.id == 3 }.addExperience(50)
        }
        panel.add(button, con)*/

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

    init {
        val bronzeTrophy = IconLoader.getIcon("userInterface/BronzeTrophy.svg", javaClass)
        val silverTrophy = IconLoader.getIcon("userInterface/SilverTrophy.svg", javaClass)
        val goldTrophy = IconLoader.getIcon("userInterface/GoldTrophy.svg", javaClass)

        achievementList.add(
            Achievement(1, goldTrophy, "Click!!!", "Click the buttons", 0, 20)
        )


        /*achievementList.add(
            Achievement(2, silverTrophy, "Test 2", "Integer auctor vulputate rutrum. Curabitur porttitor tempor egestas", 100, 300)
        )

        achievementList.add(
            Achievement(3, bronzeTrophy, "Test 3", "Phasellus mattis quam purus, non maximus ante fringilla vitae", 50, 100)
        )*/

        mainUI.addTab("Profile", createProfileTab())
        mainUI.addTab("Achievements", createAchievementsTab())
    }

}