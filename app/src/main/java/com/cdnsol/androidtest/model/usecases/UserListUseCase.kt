package com.cdnsol.androidtest.model.usecases

import com.cdnsol.androidtest.di.qualifier.ObserverThread
import com.cdnsol.androidtest.di.qualifier.SubscriberThread
import com.cdnsol.androidtest.model.repository.RepositoryInterface
import com.cdnsol.androidtest.model.response.UserListData
import io.reactivex.Observable
import io.reactivex.Scheduler
import javax.inject.Inject

class UserListUseCase @Inject constructor(
    private val repositoryInterface: RepositoryInterface,
    @ObserverThread val observeon: Scheduler,
    @SubscriberThread val subscribeon: Scheduler
) :
    BaseUseCase<UserListUseCase.Param, ArrayList<UserListData>>() {

    data class Param(val msg : String)

    override fun createUsesCase(param: Param?): Observable<ArrayList<UserListData>> {
        return repositoryInterface.getUsersList().subscribeOn(subscribeon)
            .observeOn(observeon)
    }
}