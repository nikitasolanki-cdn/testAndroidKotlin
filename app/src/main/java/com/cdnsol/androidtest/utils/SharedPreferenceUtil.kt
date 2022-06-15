package com.cdnsol.androidtest.utils

import android.content.SharedPreferences
import com.cdnsol.androidtest.model.response.UserDetailData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SharedPreferenceUtil(val preferences: SharedPreferences, val gson: Gson) {

    companion object {
        val USER_DETAIL = "USER_DETAIL"


    }

    fun getString(pref_name: String?, defValue: String?): String? {
        return preferences.getString(pref_name, defValue)
    }

    fun putString(pref_name: String?, pref_val: String?) {
        Thread {
            val editor = preferences.edit()
            editor.putString(pref_name, pref_val)
            editor.apply()
        }.start()
    }

    fun getInt(pref_name: String?, defValue: Int): Int {
        return preferences.getInt(pref_name, defValue)
    }

    fun putInt(pref_name: String?, pref_val: Int?) {
        val editor = preferences.edit()
        editor.putInt(pref_name, pref_val!!)
        editor.apply()
    }

    fun getBoolean(pref_name: String?, defValue: Boolean): Boolean {
        return preferences.getBoolean(pref_name, defValue)
    }

    fun putBoolean(pref_name: String?, pref_val: Boolean?) {
        val editor = preferences.edit()
        editor.putBoolean(pref_name, pref_val!!)
        editor.apply()
    }

    fun putUserDetailInList(userDetailData: UserDetailData) {
        val userDetailList = getUserDetailList()
        if (getUserDetailList().size != 0) {
            if (!getUserDetailList().contains(userDetailData)) {
                userDetailList.add(0, userDetailData)
            }
        } else {
            userDetailList.add(0, userDetailData)
        }

        val editor = preferences.edit()
        val json: String = gson.toJson(userDetailList)
        editor.putString(USER_DETAIL, json)
        editor.apply()
    }

    fun getUserDetailList(): ArrayList<UserDetailData?> {
        var userDetailList: ArrayList<UserDetailData?> = ArrayList<UserDetailData?>()

        val gson = Gson()
        val json = preferences.getString(USER_DETAIL, "")
        if (json!!.isEmpty()) {
            userDetailList = ArrayList<UserDetailData?>()
        } else {
            val type = object : TypeToken<ArrayList<UserDetailData?>>() {}.type
            userDetailList = gson.fromJson<ArrayList<UserDetailData?>>(json, type)
        }
        return userDetailList
    }


}