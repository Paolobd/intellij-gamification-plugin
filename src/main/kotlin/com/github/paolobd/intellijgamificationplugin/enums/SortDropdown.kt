package com.github.paolobd.intellijgamificationplugin.enums

enum class SortDropdown(
    val text: String
) {
    DEFAULT("Default"),
    ALPHABETIC_ASC("A-Z"),
    ALPHABETIC_DSC("Z-A"),
    COMPLETION_ASC("Low to High Completion"),
    COMPLETION_DSC("High to Low Completion")
}