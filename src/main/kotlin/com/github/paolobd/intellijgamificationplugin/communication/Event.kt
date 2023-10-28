package com.github.paolobd.intellijgamificationplugin.communication

import com.fasterxml.jackson.annotation.JsonProperty

data class Event(
    @JsonProperty("id") var id: String = "",
    @JsonProperty("url") var url: String = "",
    @JsonProperty("eventType") var eventType: EventType = EventType.LOCATOR
)