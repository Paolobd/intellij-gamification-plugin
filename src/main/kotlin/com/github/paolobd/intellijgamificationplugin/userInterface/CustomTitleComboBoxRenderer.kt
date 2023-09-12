package com.github.paolobd.intellijgamificationplugin.userInterface

import com.github.paolobd.intellijgamificationplugin.dataProviders.TitleDataProvider
import com.github.paolobd.intellijgamificationplugin.services.ApplicationStatePersistence
import com.intellij.ui.JBColor
import java.awt.Component
import javax.swing.DefaultListCellRenderer
import javax.swing.JLabel
import javax.swing.JList
import javax.swing.ListCellRenderer

class CustomTitleComboBoxRenderer : ListCellRenderer<Any> {
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

        val userLevel = ApplicationStatePersistence.getInstance().state.userState.level
        val titleLevel = TitleDataProvider().getTitleById(index).level

        if (titleLevel > userLevel) {
            renderer.foreground = JBColor.GRAY
        }

        renderer.toolTipText = renderer.text

        return renderer
    }
}