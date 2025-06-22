package com.grhuan.cat.utils

import android.content.Context

class PreferencesUtils (context: Context){

    val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object{
        const val PREFS_NAME = "com.grhuan.cat.KEY_API"
        const val KEY_API = ""
    }

    fun getKeyApi() : String? {
        return prefs.getString(KEY_API, null)
    }

    fun saveKeyApi(apiKey: String){
        with(prefs.edit()){
            putString(KEY_API, apiKey)
                .apply()
        }
    }
}