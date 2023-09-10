package com.github.paolobd.intellijplugintemplate.views

import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.DialogWrapper
import com.intellij.openapi.util.IconLoader
import com.intellij.ui.components.JBList
import java.awt.*
import javax.swing.*

class EditUserDialog : DialogWrapper(true) {
    private val textField = JTextField(20)
    private val dropdown = ComboBox(arrayOf("I'm asleep", "Omegalul"))
    private val imageGrid = JBList(arrayOf("Image 1", "Image 2"))
    init {
        title = "Edit User"
        setOKButtonText("Save")
        init()
    }
    override fun createCenterPanel(): JComponent {
        val panel = JPanel()
        panel.layout = GridBagLayout()
        val gbc = GridBagConstraints()
        gbc.gridy = 0

        val nameTitlePanel = JPanel(GridLayout(2,2))

        nameTitlePanel.add(JLabel("Insert name"))
        nameTitlePanel.add(textField)

        nameTitlePanel.add(JLabel("Select tile"))
        nameTitlePanel.add(dropdown)

        panel.add(nameTitlePanel, gbc)

        gbc.gridy ++

        val iconLabel = JLabel("Choose icon")
        iconLabel.font = Font(iconLabel.font.name, Font.PLAIN, 16)
        iconLabel.border = BorderFactory.createEmptyBorder(10, 0, 10, 0)
        panel.add(iconLabel, gbc)

        val iconPanel = JPanel()
        iconPanel.layout = GridLayout(0, 5)
        for(i in 1..10){
            val icon = IconLoader.getIcon("/userInterface/user/user.svg", javaClass)
            val label = JLabel(icon)
            label.size = Dimension(50, 50)
            iconPanel.add(label)
        }

        gbc.gridy ++
        panel.add(iconPanel, gbc)

        gbc.gridy ++
        gbc.weighty = 1.0
        panel.add(JLabel(), gbc)

        return panel
    }

    override fun doOKAction() {

        super.doOKAction()
    }
}