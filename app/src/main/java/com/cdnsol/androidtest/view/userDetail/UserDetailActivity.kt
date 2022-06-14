package com.cdnsol.androidtest.view.userDetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cdnsol.androidtest.R
import com.cdnsol.androidtest.TestApp
import com.cdnsol.androidtest.databinding.ActivityHomeBinding
import com.cdnsol.androidtest.databinding.ActivityUserDetailBinding
import com.cdnsol.androidtest.di.component.ActivityComponent
import com.cdnsol.androidtest.di.component.DaggerActivityComponent
import com.cdnsol.androidtest.di.module.ActivityModule
import com.cdnsol.androidtest.utils.AndroiddUtils
import com.cdnsol.androidtest.view.base.BaseActivity
import com.cdnsol.androidtest.view.base.GlobalViewModelFactory
import com.cdnsol.androidtest.view.home.HomeViewModel
import com.cdnsol.androidtest.view.home.UsersListAdapter
import javax.inject.Inject

class UserDetailActivity : BaseActivity() {
    @Inject
    lateinit var factory: GlobalViewModelFactory<UserDetailViewModel>
    private lateinit var detailBinding: ActivityUserDetailBinding

    //ViewModel
    val viewModel: UserDetailViewModel by lazy {
        ViewModelProvider(this, factory).get(UserDetailViewModel::class.java)
    }

    override val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .appComponent((application as TestApp).component).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        activityComponent.inject(this)
        detailBinding.viewModel = viewModel
        detailBinding.lifecycleOwner = this
        if (intent != null) {
            var loginId: String? = intent.getStringExtra("LOGIN");
            if (loginId != null)
                viewModel.getUserDetail(loginId)
        }
        setObservers()

    }
    private fun setObservers() {
        viewModel.liveDataisLoading.observe(this, {
            it?.let {
                if (it) {
                    AndroiddUtils.showProgressDialog(this)
                } else {
                    AndroiddUtils.hideProgressDialog(this)
                }
            }
        })
//        viewModel.userList.observe(this, {
//            it?.let {
//                adapter = UsersListAdapter(this, viewModel.userList.value!!)
//                homeBinding.rvUsers.adapter = adapter
//            }
//        })
        viewModel.showErrorCode.observe(this, Observer { code ->
            code?.let {
                showToast(getString(it))
            }

        })
        viewModel.showServerMesage.observe(this, Observer { code ->
            code?.let {
                showToast(it)
            }

        })
        viewModel.showErrorAlert.observe(this, Observer {
            it?.let {
                // showAlert(it)
                showToast(it)
            }
        })
    }


}