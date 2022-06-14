package com.cdnsol.androidtest.model.repository

import com.cdnsol.androidtest.model.response.UserDetailData
import com.cdnsol.androidtest.model.response.UserListData
import com.cdnsol.androidtest.model.usecases.UserDetailUseCase
import io.reactivex.Observable

interface RepositoryInterface {
    fun getUsersList():Observable<ArrayList<UserListData>>
    fun getUserDetail(login : String):Observable<UserDetailData>

}