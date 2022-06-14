package com.cdnsol.androidtest.di.component

import com.cdnsol.androidtest.di.module.FragmentModule
import com.cdnsol.androidtest.di.scope.FragmentScope
import dagger.Component


@Component(
    dependencies = arrayOf(ActivityComponent::class),
    modules = arrayOf(FragmentModule::class)
)
@FragmentScope
interface FragmentComponent {

}