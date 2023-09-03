package com.github.paolobd.intellijplugintemplate.library

import com.fasterxml.jackson.annotation.JsonProperty

data class Event(
    @JsonProperty("id") var id: String,
    @JsonProperty("url") var url: String,
    @JsonProperty("eventType") var eventType: EventType){
}