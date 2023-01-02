package com.ayd.wineapp.utils

class Constants {

    companion object{

        /** Retrofit */
        const val BASE_URL = "https://api.sampleapis.com"
        //queries...

        const val GET_URL_RED = "/wines/reds"
        const val GET_URL_WHITE = "/wines/whites"
        const val GET_URL_SPARKLING = "/wines/sparkling"
        const val GET_URL_ROSE = "/wines/rose"


        /** Store user data */
        const val SHARED_PREF_BOARD = "ONBOARD"
        const val SHARED_PREF_EDIT = "FINISHED"

    }

}