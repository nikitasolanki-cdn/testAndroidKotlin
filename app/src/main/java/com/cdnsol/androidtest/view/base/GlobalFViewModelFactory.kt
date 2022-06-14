package com.cdnsol.androidtest.view.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cdnsol.androidtest.di.scope.FragmentScope
import javax.inject.Inject


/**
 * Viewmodel Factory for FragmentScope
 */

@FragmentScope
class GlobalFViewModelFactory<T : ViewModel> @Inject constructor(val viewModel: T) : ViewModelProvider.Factory {

    companion object {
        val  TAG:String  = "FViewModelFactory"
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModel as T
    }

}
