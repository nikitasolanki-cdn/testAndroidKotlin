package com.cdnsol.androidtest.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity:Activity)
{
    @Provides
    fun getActivityContext(): Activity {
        return activity
    }
}
