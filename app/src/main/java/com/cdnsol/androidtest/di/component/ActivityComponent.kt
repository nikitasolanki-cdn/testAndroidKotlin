package com.cdnsol.androidtest.di.component


import com.cdnsol.androidtest.di.module.ActivityModule
import com.cdnsol.androidtest.di.qualifier.ObserverThread
import com.cdnsol.androidtest.di.qualifier.SubscriberThread
import com.cdnsol.androidtest.di.scope.ActivityScope
import com.cdnsol.androidtest.model.repository.RepositoryInterface
import com.cdnsol.androidtest.view.home.HomeActivity
import com.cdnsol.androidtest.view.userDetail.UserDetailActivity
import dagger.Component
import io.reactivex.Scheduler


@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class) , modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun repository(): RepositoryInterface
    @ObserverThread
    fun observerThread(): Scheduler
    @SubscriberThread
    fun subscriberThread(): Scheduler
    fun inject(homeActivity: HomeActivity)
    fun inject(userDetailActivity: UserDetailActivity)
}