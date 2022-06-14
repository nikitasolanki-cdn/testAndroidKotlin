package com.cdnsol.androidtest.model.repository

import com.cdnsol.androidtest.model.response.UserListData
import com.cdnsol.androidtest.service.ApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class Repository @Inject constructor(val apiInterface: ApiInterface):RepositoryInterface{
    override fun getUsersList(): Observable<ArrayList<UserListData>> {
      return apiInterface.getUsersList()
    }
}