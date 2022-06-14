package com.cdnsol.androidtest.service

import com.cdnsol.androidtest.model.response.UserListData
import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {
    companion object {
        const val DOMAIN = "https://api.github.com/"

        const val BASE_URL = "${DOMAIN}"
    }
    @GET("users")
    fun getUsersList(): Observable<ArrayList<UserListData>>

}