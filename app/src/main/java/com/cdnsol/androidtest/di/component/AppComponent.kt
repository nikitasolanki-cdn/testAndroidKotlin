package com.cdnsol.androidtest.di.component

import android.content.Context
import com.cdnsol.androidtest.TestApp
import com.cdnsol.androidtest.di.module.AppModule
import com.cdnsol.androidtest.di.module.NetworkModule
import com.cdnsol.androidtest.di.qualifier.ObserverThread
import com.cdnsol.androidtest.di.qualifier.SubscriberThread
import com.cdnsol.androidtest.model.repository.RepositoryInterface
import com.cdnsol.androidtest.utils.SharedPreferenceUtil
import dagger.Component
import io.reactivex.Scheduler
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {
    fun context(): Context
    fun inject(app: TestApp)
    fun repository(): RepositoryInterface
    fun sharedPrefUtil(): SharedPreferenceUtil
    @ObserverThread
    fun observerThread(): Scheduler

    @SubscriberThread
    fun subscriberThread(): Scheduler

}