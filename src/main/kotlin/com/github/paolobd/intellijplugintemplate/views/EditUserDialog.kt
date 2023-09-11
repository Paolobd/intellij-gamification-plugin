package com.github.paolobd.intellijplugintemplate.views

import com.github.paolobd.intellijplugintemplate.dataProviders.TitleDataProvider
import com.github.paolobd.intellijplugintemplate.dataProviders.UserIconDataProvider
import com.github.paolobd.intellijplugintemplate.services.ApplicationStatePersistence
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.ui.JBColor
import com.intellij.ui.components.JBScrollPane
import java.awt.*
import javax.swing.*

class EditUserDialog : DialogWrapper(true) {
    private var nameLabel = JTextField()
    private var titleId = 0
    private var selectedUserIcon = JButton()
    private var iconId = 0

    init {
        title = "Edit User"
        setOKButtonText("Save")
        init()
    }

    override fun createCenterPanel(): JComponent {
        val userState = ApplicationStatePersistence.getInstance().state.userState

        val panel = JPanel()
        panel.layout = GridBagLayout()
        val gbc = GridBagConstraints()
        gbc.gridy = 0

        val nameTitlePanel = JPanel(GridLayout(2, 2))
        nameLabel = JTextField(20)
        nameLabel.text = userState.name
        nameTitlePanel.add(JLabel("Insert name"))
        nameTitlePanel.add(nameLabel)

        val titles = TitleDataProvider().titles.map {
            if (it.level > userState.level)
                "${it.text} [Locked]"
            else
                it.text
        }.toTypedArray()

        val dropdown = ComboBox(titles)
        titleId = userState.titleId
        dropdown.selectedIndex = titleId
        dropdown.renderer = CustomComboBoxRenderer()

        dropdown.addActionListener {
            val titleLevel = TitleDataProvider().getTitleById(dropdown.selectedIndex).level
            if (titleLevel > userState.level) {
                dropdown.selectedIndex = titleId
            } else {
                titleId = dropdown.selectedIndex
            }
        }

        nameTitlePanel.add(JLabel("Select tile"))
        nameTitlePanel.add(dropdown)

        panel.add(nameTitlePanel, gbc)

        gbc.gridy++

        val iconLabel = JLabel("Choose icon")
        iconLabel.font = Font(iconLabel.font.name, Font.PLAIN, 16)
        iconLabel.border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
        panel.add(iconLabel, gbc)

        val iconPanel = JPanel(GridLayout(0, 5))
        iconId = userState.iconId

        for ((index, userIcon) in UserIconDataProvider().icons.withIndex()) {
            val icon = Icons().loadUserMiniatureIcon(userIcon.fileName)
            val button = JButton(icon)
            val emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5)
            val lineBorder = BorderFactory.createLineBorder(JBColor.BLACK, 2)
            val compoundBorder = BorderFactory.createCompoundBorder(emptyBorder, lineBorder)

            if (userIcon.level > userState.level) {
                button.toolTipText = "Unlocked at level ${userIcon.level}"
            }

            if (index == iconId) {
                button.border = compoundBorder
                selectedUserIcon = button
            } else {
                button.border = emptyBorder
            }

            button.addActionListener {
                if (userIcon.level <= userState.level && iconId != index) {
                    selectedUserIcon.border = emptyBorder

                    button.border = compoundBorder
                    iconId = index
                    selectedUserIcon = button
                }
            }

            iconPanel.add(button)
        }

        gbc.gridy++
        panel.add(JBScrollPane(iconPanel), gbc)

        gbc.gridy++
        gbc.weighty = 1.0
        panel.add(JLabel(), gbc)

        return panel
    }

    override fun doOKAction() {
        ApplicationStatePersistence.getInstance().changeUserInfo(nameLabel.text.trim(), titleId, iconId)
        super.doOKAction()
    }
}

class CustomComboBoxRenderer : ListCellRenderer<Any> {
    private val defaultRenderer: ListCellRenderer<in Any> = DefaultListCellRenderer()

    override fun getListCellRendererComponent(
        list: JList<out Any>?,
        value: Any?,
        index: Int,
        isSelected: Boolean,
        cellHasFocus: Boolean
    ): Component {
        val renderer =
            defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus) as JLabel

        renderer.toolTipText = renderer.text

        return renderer
    }
}
