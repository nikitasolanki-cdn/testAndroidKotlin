package com.cdnsol.androidtest.service

import com.cdnsol.androidtest.model.response.UserDetailData
import com.cdnsol.androidtest.model.response.UserListData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {
    companion object {
        const val DOMAIN = "https://api.github.com/"

        const val BASE_URL = "${DOMAIN}"
    }
    @GET("users")
    fun getUsersList(): Observable<ArrayList<UserListData>>

    @GET("users/{login}")
    fun getUserDetail(@Path("login") login : String):Observable<UserDetailData>

}