package com.mns.therkc.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class ComplexPreferences(internal var context: Context) {

    private val preferenceId = "com.mns.therkc"

    companion object {
        lateinit var preferences: SharedPreferences
        lateinit var editor: SharedPreferences.Editor
        val GSON: Gson = Gson()
    }

    init {
        preferences = context.getSharedPreferences(preferenceId, Context.MODE_PRIVATE)
        editor = preferences.edit()
    }


    fun setObject(key: String?, `object`: Any?) {

        if (`object` == null) {
            throw IllegalArgumentException("object is null")
        }

        if (key == "" || key == null) {
            throw IllegalArgumentException("key is empty or null")
        }

        editor.putString(key, GSON.toJson(`object`))
        editor.apply()
    }

    fun <T> getObject(key: String, a: Class<T>): T? {
        val gson = preferences.getString(key, null)
        return if (gson == null) {
            null
        } else {
            try {
                GSON.fromJson(gson, a)
            } catch (e: Exception) {
                throw IllegalArgumentException("Object stored with key $key is instanceof other class")
            }
        }
    }


    fun commit() {
        editor.commit()
    }

    fun getPref(mPrefKey: String, mPrefValue: String?): String? {
        return try {
            /*   val prefs = context.getSharedPreferences(preferenceId, Context.MODE_PRIVATE)*/
            preferences.getString(mPrefKey, mPrefValue)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }

    fun setPref(mPrefKey: String, mPrefValue: String?) {
        try {
            /*val editor = context.getSharedPreferences(preferenceId, Context.MODE_PRIVATE).edit()*/
            if (mPrefValue != null) {
                editor.remove(mPrefKey)
                editor.putString(mPrefKey, mPrefValue)
            } else {
                editor.remove(mPrefKey)
                editor.putString(mPrefKey, "")
            }
            editor.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun logoutUser() {
        editor.remove("user")
        editor.commit()
        editor.remove("current_student")
        editor.commit()
        editor.remove("student")
        editor.commit()
        editor.clear()
        editor.commit()


    }


}