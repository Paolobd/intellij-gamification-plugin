package com.github.paolobd.intellijplugintemplate.library

data class Event(var element: String, var eventType: EventType){
    constructor() : this("", EventType.DEFAULT)
}