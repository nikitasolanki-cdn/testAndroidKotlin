package com.cdnsol.androidtest.view.userDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.cdnsol.androidtest.R
import com.cdnsol.androidtest.model.response.UserDetailData
import com.cdnsol.androidtest.model.usecases.UserDetailUseCase
import com.cdnsol.androidtest.utils.CommonUtils
import com.cdnsol.androidtest.view.base.BaseViewModel
import com.cdnsol.androidtest.view.base.plusAssign
import javax.inject.Inject

class UserDetailViewModel @Inject constructor(
    val userDetailUseCase: UserDetailUseCase
) : BaseViewModel() {
    val showErrorCode = MutableLiveData<Int>()
    val showServerMesage = MutableLiveData<String>()
    val useDetailData = MutableLiveData<UserDetailData>()

    fun getUserDetail(login : String){
        liveDataisLoading.value = true
        if (CommonUtils.isInternetAvailable()) {
            disposable += userDetailUseCase.execute(UserDetailUseCase.Param(login)).subscribe(
                {
                    liveDataisLoading.value = false
                    useDetailData.value = it
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