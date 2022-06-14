package com.cdnsol.androidtest.utils

import android.content.Context
import android.net.ConnectivityManager
import com.cdnsol.androidtest.TestApp


class CommonUtils {
    companion object {
        fun isInternetAvailable(): Boolean {
            val connectivityManager =
                TestApp.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            val activeNetworkInfo = connectivityManager!!.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}