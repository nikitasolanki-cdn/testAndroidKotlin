package com.cdnsol.androidtest.view.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cdnsol.androidtest.R
import com.cdnsol.androidtest.TestApp
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.json.JSONObject

/**
 * Base For Viewmodels
 */
abstract class BaseViewModel() : ViewModel(){

    //livedata to show progress/hide
    val liveDataisLoading = MutableLiveData<Boolean>()

    //livedata to show Message
    val liveDatashowMessage = MutableLiveData<String>()

    val showErrorAlert = MutableLiveData<String>()


    val disposable = CompositeDisposable()

    fun getErrorMsg(error: HttpException):String{
        try {
            val errorBody = error.response().errorBody()?.string()
            val errorObj = JSONObject(errorBody)
            return errorObj.getString("message")
        }catch (e:Exception){
            e.printStackTrace()
        }
        return TestApp.instance.getString(R.string.server_responce_error)
    }

    fun customeErrorCode(error: HttpException):Boolean{
        return (error.code()==401||error.code()==404 || error.code() == 400)
    }

    fun getString(msg: Int): String {
        return TestApp.instance.getResources().getString(msg)
    }

    override fun onCleared() {
        super.onCleared()
        //clearing All Observer subscriptions
        if(!disposable.isDisposed){
            disposable.dispose()
        }
    }

}

/**
 * plus operator overload to Disposable
 */
operator fun CompositeDisposable.plusAssign(disposable: Disposable) {
    add(disposable)
}



