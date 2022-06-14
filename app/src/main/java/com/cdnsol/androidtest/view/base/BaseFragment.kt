package com.cdnsol.androidtest.view.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.cdnsol.androidtest.di.component.DaggerFragmentComponent
import com.cdnsol.androidtest.di.component.FragmentComponent
import com.cdnsol.androidtest.view.base.BaseActivity

/**
 * Base For Fragments
 */
open class BaseFragment : Fragment() {
    lateinit var mActivity: BaseActivity

    val fragmentComponent: FragmentComponent by lazy {
        DaggerFragmentComponent.builder().activityComponent(mActivity.activityComponent)
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = activity as BaseActivity

    }

    fun showToast(msg: String) {
        mActivity.showToast(msg)
    }


}
