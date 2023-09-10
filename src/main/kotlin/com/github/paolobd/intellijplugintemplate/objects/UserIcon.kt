package com.github.paolobd.intellijplugintemplate.objects

enum class UserIcon(
    val path: String,
    val level: Int
) {
    USER("/userInterface/user/user.svg", 0),
    CAT("/userInterface/user/cat.svg", 1),

}