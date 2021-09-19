package com.mns.banzosapp.helper.base

import android.content.Context
import android.content.SharedPreferences

class ApplicationPrefs(private val context: Context) {

    private lateinit var prefsT: ApplicationPrefs

    private val PREF_NAME = "banzos_prefs"

    private val USER_ID = "user_id"
    private val USER_DETAILS = "user_details"
    private val FCM_TOKEN = "fcm_token"
    private val USER_TOKEN = "user_token"

    fun setUserId(userId: String?) {
        setPreferencesData(USER_ID, userId)
    }

    fun getUserId(): String? {
        return getPreferenceData(USER_ID, null)
    }

/*    fun setUserDetails(userDetail: UserDetails?) {
        val gson = Gson()
        setPreferencesData(USER_DETAILS, gson.toJson(userDetail))
    }

    fun getUserDetails(): UserDetails {
        val gson = Gson()
        return gson.fromJson(getPreferenceData(USER_DETAILS, ""), UserDetails::class.java)
    }*/

    fun setFCMToken(userToken: String?) {
        setPreferencesData(FCM_TOKEN, userToken)
    }

    fun getFCMToken(): String? {
        return getPreferenceData(FCM_TOKEN, "")
    }

    fun isLogin(): Boolean {
        return getUserId() != null && getUserId()!!.trim { it <= ' ' }.isNotEmpty()
    }

    fun clearUser() {
        setUserId(null)
//        setUserDetails(null)
        setUserToken(null)
    }

    fun setUserToken(userToken: String?) {
        setPreferencesData(USER_TOKEN, userToken)
    }

    fun getUserToken(): String? {
        return getPreferenceData(USER_TOKEN, "")
    }

    fun clear() {
        getPrefsEditor().clear().commit()
    }

    private fun getPrefs(): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    private fun getPrefsEditor(): SharedPreferences.Editor {
        return getPrefs().edit()
    }

    /*
     * Save string data type
     *
     * */
    private fun setPreferencesData(key: String, value: String?) {
        val editor = getPrefsEditor()
        editor.putString(key, value)
        editor.commit()
    }

    /*
     * Save Int data type
     *
     * */
    private fun setPreferencesData(key: String, value: Int) {
        val editor = getPrefsEditor()
        editor.putInt(key, value)
        editor.commit()
    }

    /*
     * Save Long data type
     *
     * */
    private fun setPreferencesData(key: String, value: Long) {
        val editor = getPrefsEditor()
        editor.putLong(key, value)
        editor.commit()
    }

    /*
     * Save boolean data type
     *
     * */
    private fun setPreferencesData(key: String, value: Boolean) {
        val editor = getPrefsEditor()
        editor.putBoolean(key, value)
        editor.commit()
    }

    /*
     * Save float data type
     *
     * */
    private fun setPreferencesData(key: String, value: Float) {
        val editor = getPrefsEditor()
        editor.putFloat(key, value)
        editor.commit()
    }

    /*
     *
     * fetch  method for pref
     *
     * */

    /*
     *
     * fetch  method for pref
     *
     * */
    /*
     *  getPreferenceData String data from pref
     * */
    private fun getPreferenceData(key: String, defaultValue: String?): String? {
        return getPrefs().getString(key, defaultValue)
    }

    /*
     *  getPreferenceData int data from pref
     * */
    private fun getPreferenceData(key: String, defaultValue: Int): Int {
        return getPrefs().getInt(key, defaultValue)
    }

    /*
     *  getPreferenceData boolean data from pref
     * */
    private fun getPreferenceData(key: String, defaultValue: Boolean): Boolean {
        return getPrefs().getBoolean(key, defaultValue)
    }

    /*
     *  getPreferenceData long data from pref
     * */
    private fun getPreferenceData(key: String, defaultValue: Long): Long {
        return getPrefs().getLong(key, defaultValue)
    }
}