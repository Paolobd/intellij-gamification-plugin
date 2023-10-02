package com.github.paolobd.intellijgamificationplugin.enums

enum class SortDropdown(
    val text: String
) {
    DEFAULT("Default"),
    ALPHABETIC_ASC("A-Z"),
    ALPHABETIC_DSC("Z-A"),
    COMPLETION_ASC("Completion rate % up"),
    COMPLETION_DSC("Completion rate % down")
}