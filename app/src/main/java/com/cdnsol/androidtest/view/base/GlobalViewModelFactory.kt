package com.cdnsol.androidtest.view.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cdnsol.androidtest.di.scope.ActivityScope
import javax.inject.Inject

/**
 * Viewmodel Factory for ActivityScope
 */
@ActivityScope
class GlobalViewModelFactory<T : ViewModel> @Inject constructor(val viewModel: T) : ViewModelProvider.Factory {

   companion object {
       val  TAG:String  = "GlobalViewModelFactory"
   }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        Log.e(TAG,"created")
        return viewModel as T
    }

}
