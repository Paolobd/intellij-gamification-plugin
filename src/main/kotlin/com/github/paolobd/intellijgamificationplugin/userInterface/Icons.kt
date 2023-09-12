package com.github.paolobd.intellijgamificationplugin.userInterface

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

class Icons {

    fun loadUserIcon(name: String): Icon {
        return loadIcon("/userInterface/user/full/${name}")
    }

    fun loadUserMiniatureIcon(name: String): Icon {
        return loadIcon("/userInterface/user/miniature/${name}")
    }

    fun loadGlobalAchIcon(name: String): Icon {
        return loadIcon("/userInterface/global/${name}")
    }

    fun loadProjectAchIcon(name: String): Icon {
        return loadIcon("/userInterface/project/${name}")
    }

    fun loadEditIcon(): Icon {
        return loadIcon("/userInterface/edit.svg")
    }

    fun loadEmptyIcon(): Icon {
        return loadIcon("/userInterface/empty.svg")
    }

    private fun loadIcon(path: String): Icon {
        return IconLoader.getIcon(path, javaClass)
    }

}