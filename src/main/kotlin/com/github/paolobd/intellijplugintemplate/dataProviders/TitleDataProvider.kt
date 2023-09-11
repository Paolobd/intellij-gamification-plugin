package com.github.paolobd.intellijplugintemplate.dataProviders

import com.github.paolobd.intellijplugintemplate.dataClasses.Title

class TitleDataProvider {
    val titles = listOf(
        Title("Just Started!", 1),
        Title("Noob!", 1),
        Title("Getting the hang of it!", 3)
    )

    fun getTitleById(id: Int): Title {
        if(id >= 0 && id < titles.size){
            return titles[id]
        }
        return titles[0]
    }
}