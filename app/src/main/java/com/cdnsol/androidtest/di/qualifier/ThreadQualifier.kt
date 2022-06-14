package com.cdnsol.androidtest.di.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class SubscriberThread

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ObserverThread
