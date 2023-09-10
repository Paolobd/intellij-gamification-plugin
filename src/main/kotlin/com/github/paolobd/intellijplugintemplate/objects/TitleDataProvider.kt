package com.github.paolobd.intellijplugintemplate.objects

class TitleDataProvider {
    val titles = listOf(
        Title("Just Started!", 1),
        Title("Noob!", 1)
    )

    fun getTitleById(id: Int): Title {
        if(id >= 0 && id < titles.size){
            return titles[id]
        }
        return titles[0]
    }
}