package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataProviders.TitleDataProvider
import com.github.paolobd.intellijgamificationplugin.dataProviders.UserIconDataProvider
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBScrollPane
import java.awt.*
import javax.swing.*

class EditUserDialog : DialogWrapper(true) {
    private var nameLabel = JTextField()
    private var titleId = 1
    private var selectedUserIcon = JButton()
    private var iconId = 1
    private var errorCaption = JLabel()

    init {
        title = "Edit User"
        setOKButtonText("Save")
        init()
    }

    override fun createCenterPanel(): JComponent {
        val userState = ApplicationStatePersistence.getInstance().state.userState

        val panel = JPanel(GridBagLayout())
        val gbc = GridBagConstraints()
        gbc.gridy = 0

        //Insert name row
        val nameTitlePanel = JPanel(GridLayout(2, 2))
        val insertNameLabel = JLabel("Name:")
        insertNameLabel.font = Font(insertNameLabel.font.name, Font.PLAIN, 16)
        nameLabel = JTextField(20)
        nameLabel.text = userState.name
        errorCaption = JLabel("Max 20 char")
        errorCaption.font = Font(errorCaption.font.name, Font.PLAIN, 10)
        errorCaption.border = BorderFactory.createEmptyBorder(0, 5, 0, 0)

        val fieldPanel = JPanel(BorderLayout())
        fieldPanel.add(nameLabel, BorderLayout.NORTH)
        fieldPanel.add(errorCaption, BorderLayout.SOUTH)

        nameTitlePanel.add(insertNameLabel)
        nameTitlePanel.add(fieldPanel)

        //Select title row
        val selectTileLabel = JLabel("Title:")
        selectTileLabel.font = Font(selectTileLabel.font.name, Font.PLAIN, 16)

        val titles = TitleDataProvider.titles.map {
            if (it.level > userState.level)
                "${it.text} [Level ${it.level}]"
            else
                it.text
        }.toTypedArray()

        val dropdown = ComboBox(titles)
        titleId = userState.titleId
        dropdown.selectedIndex = TitleDataProvider.getIndexById(titleId)
        dropdown.renderer = CustomTitleComboBoxRenderer()

        dropdown.addActionListener {
            val titleLevel = TitleDataProvider.titles[dropdown.selectedIndex].level
            if (titleLevel > userState.level) {
                dropdown.selectedIndex = TitleDataProvider.getIndexById(titleId)
            } else {
                titleId = TitleDataProvider.titles[dropdown.selectedIndex].id
            }
        }

        nameTitlePanel.add(selectTileLabel)
        nameTitlePanel.add(dropdown)

        panel.add(nameTitlePanel, gbc)

        //User Icon label
        val allIconsLabel = JLabel("Choose icon")
        allIconsLabel.font = Font(allIconsLabel.font.name, Font.PLAIN, 16)
        allIconsLabel.border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
        gbc.gridy++
        panel.add(allIconsLabel, gbc)

        //User Icon grid
        val allIconsPanel = JPanel(GridLayout(0, 5))
        iconId = userState.iconId

        for (userIcon in UserIconDataProvider.icons) {
            val iconPanel = JPanel()
            iconPanel.layout = BorderLayout()

            val icon = IconUtility().loadUserMiniatureIcon(userIcon.fileName)
            val iconButton = JButton(icon)
            val iconLabel = JLabel()
            iconLabel.font = Font(iconLabel.font.name, Font.PLAIN, 10)
            iconLabel.horizontalAlignment = SwingConstants.CENTER

            val emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5)
            val lineBorder = BorderFactory.createLineBorder(JBColor.BLACK, 2)
            val bevelBorder = BorderFactory.createRaisedSoftBevelBorder()
            val selectedBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder)
            val unavailableBorder = BorderFactory.createCompoundBorder(emptyBorder, bevelBorder)
            val unselectedBorder = BorderFactory.createEmptyBorder(7, 7, 7, 7)

            if (userIcon.level > userState.level) {
                iconButton.border = unavailableBorder
                iconLabel.text = "Level ${userIcon.level}"
            } else {
                iconButton.border = unselectedBorder
                iconLabel.text = "Unlocked"
                if (userIcon.id == iconId) {
                    iconButton.border = selectedBorder
                    selectedUserIcon = iconButton
                }
            }

            iconPanel.add(iconButton, BorderLayout.NORTH)
            iconPanel.add(iconLabel, BorderLayout.SOUTH)
            allIconsPanel.add(iconPanel)

            iconButton.addActionListener {
                if (userIcon.level <= userState.level && iconId != userIcon.id) {
                    selectedUserIcon.border = unselectedBorder

                    iconButton.border = selectedBorder
                    iconId = userIcon.id
                    selectedUserIcon = iconButton
                }
            }
        }

        gbc.gridy++
        panel.add(JBScrollPane(allIconsPanel), gbc)

        gbc.gridy++
        gbc.weighty = 1.0
        panel.add(JLabel(), gbc)

        return panel
    }

    override fun doOKAction() {
        if (nameLabel.text.trim().length > 20) {
            errorCaption.foreground = JBColor.RED
            errorCaption.font = Font(errorCaption.font.name, Font.BOLD, 10)
            return
        }

        ApplicationStatePersistence.getInstance().changeUserInfo(nameLabel.text.trim(), titleId, iconId)
        super.doOKAction()
    }
}
