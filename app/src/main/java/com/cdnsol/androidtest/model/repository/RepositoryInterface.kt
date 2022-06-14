package com.cdnsol.androidtest.model.repository

import com.cdnsol.androidtest.model.response.UserListData
import io.reactivex.Observable

interface RepositoryInterface {
    fun getUsersList():Observable<ArrayList<UserListData>>

}