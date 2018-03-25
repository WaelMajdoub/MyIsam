package com.example.mejdo.myisam.model

/**
 * Created by lenovo on 11/03/2018.
 */
class Events (val eventId: String, val name_event: String, val description: String, val formateur: String, val date: String ,val prix:String ,val etat:String ,val  idclub:String) {
    constructor(): this ("","","","","","","","")

}