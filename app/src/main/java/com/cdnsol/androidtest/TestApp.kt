package com.cdnsol.androidtest

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.cdnsol.androidtest.di.component.AppComponent
import com.cdnsol.androidtest.di.component.DaggerAppComponent
import com.cdnsol.androidtest.di.module.AppModule
import com.cdnsol.androidtest.di.module.NetworkModule
import com.cdnsol.androidtest.service.ApiInterface

class TestApp : Application() {
    companion object {
        lateinit var instance: TestApp
            private set

    }
    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
             .networkModule(NetworkModule(ApiInterface.BASE_URL)).build()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        component.inject(this)

    }
    override fun attachBaseContext(context: Context?) {
        super.attachBaseContext(context)
        MultiDex.install(context)
    }

}