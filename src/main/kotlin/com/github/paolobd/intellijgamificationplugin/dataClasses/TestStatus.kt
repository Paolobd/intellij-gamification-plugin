package com.github.paolobd.intellijgamificationplugin.dataClasses

data class TestStatus(
    var lastPassed: Boolean = false,
    var passedOnce: Boolean = false,
)
