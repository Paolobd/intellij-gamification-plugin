package com.github.paolobd.intellijgamificationplugin.userInterface

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class IconUtility {

    fun loadUserIcon(name: String): Icon {
        return loadIcon("user/full/${name}")
    }

    fun loadUserMiniatureIcon(name: String): Icon {
        return loadIcon("user/miniature/${name}")
    }

    fun loadGlobalAchIcon(name: String): Icon {
        return loadIcon("global/${name}")
    }

    fun loadProjectAchIcon(name: String): Icon {
        return loadIcon("project/${name}")
    }

    fun loadEditIcon(): Icon {
        return loadIcon("edit.svg")
    }

    fun loadEmptyIcon(): Icon {
        return loadIcon("empty.svg")
    }

    private fun loadIcon(path: String): Icon {
        val newPath = "/icons/${path}"
        return IconLoader.getIcon(newPath, javaClass)
    }

}