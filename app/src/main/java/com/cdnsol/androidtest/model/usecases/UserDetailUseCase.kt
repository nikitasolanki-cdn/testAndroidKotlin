package com.cdnsol.androidtest.model.usecases

import com.cdnsol.androidtest.di.qualifier.ObserverThread
import com.cdnsol.androidtest.di.qualifier.SubscriberThread
import com.cdnsol.androidtest.model.repository.RepositoryInterface
import com.cdnsol.androidtest.model.response.UserDetailData
import com.cdnsol.androidtest.model.response.UserListData
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class UserDetailUseCase @Inject constructor(
    private val repositoryInterface: RepositoryInterface,
    @ObserverThread val observeon: Scheduler,
    @SubscriberThread val subscribeon: Scheduler
) :
    BaseUseCase<UserDetailUseCase.Param,UserDetailData>() {

    data class Param(val login : String)

    override fun createUsesCase(param: Param?): Observable<UserDetailData> {
        return repositoryInterface.getUserDetail(param!!.login).subscribeOn(subscribeon)
            .observeOn(observeon)
    }
}