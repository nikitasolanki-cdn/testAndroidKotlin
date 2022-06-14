package com.cdnsol.androidtest.view.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cdnsol.androidtest.R
import com.cdnsol.androidtest.model.response.UserListData
import com.cdnsol.androidtest.model.usecases.UserListUseCase
import com.cdnsol.androidtest.utils.CommonUtils
import com.cdnsol.androidtest.view.base.BaseViewModel
import com.cdnsol.androidtest.view.base.plusAssign
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    val userListUseCase: UserListUseCase
) : BaseViewModel() {
    val showErrorCode = MutableLiveData<Int>()
    val showServerMesage = MutableLiveData<String>()
    val userList = MutableLiveData<ArrayList<UserListData>>()

    fun getUsersList() {
        liveDataisLoading.value = true
        if (CommonUtils.isInternetAvailable()) {
            disposable += userListUseCase.execute().subscribe(
                {
                    liveDataisLoading.value = false
                    userList.value = it
                    liveDatashowMessage.value = "Success"

                }, {
                    Log.e("Result1", "${it.message}")
                    liveDataisLoading.value = false

                }, {

                    liveDataisLoading.value = false
                }
            )

        } else {
            liveDataisLoading.value = false
            showErrorCode.value = R.string.no_internet
        }


    }
}