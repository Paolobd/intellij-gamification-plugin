package com.github.paolobd.intellijplugintemplate.library

enum class EventType {
    DEFAULT,
    CLICK,
    NAVIGATION,
    LOCATOR,
    LOCATOR_ID,
    LOCATOR_CSS,
    LOCATOR_XPATH
}

enum class Locator {
    ID,
    CLASS,
    CSS,
    LINK,
    PLINK,
    NAME,
    TAG,
    XPATH
}
