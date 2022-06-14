package com.cdnsol.androidtest.di.module

import android.content.Context
import android.content.SharedPreferences
import com.cdnsol.androidtest.TestApp
import com.cdnsol.androidtest.di.qualifier.ObserverThread
import com.cdnsol.androidtest.di.qualifier.SubscriberThread
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton


@Module
class AppModule(val app: TestApp) {

    companion object {
        val PREFERENCES = "TestApp"

    }

    @Provides
    @Singleton
    fun provideContext(): Context = app


    @Provides
    @Singleton
    @SubscriberThread
    fun provideSubscriberThread(): Scheduler = Schedulers.io()

    @Provides
    @Singleton
    @ObserverThread
    fun provideObserverThread(): Scheduler = AndroidSchedulers.mainThread()

   /* @Provides
    @Singleton
    fun provideRepository(apiInterface: ApiInterface): RepositoryInterface = Repository(apiInterface)
*/
    @Provides
    @Singleton
    fun provideSharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)

   /* @Provides
    @Singleton
    fun providePreference(preference: SharedPreferences, gson: Gson): SharedPreferenceUtil =
        SharedPreferenceUtil(preference, gson)*/
}