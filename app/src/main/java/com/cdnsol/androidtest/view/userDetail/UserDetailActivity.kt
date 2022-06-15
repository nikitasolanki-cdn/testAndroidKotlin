package com.cdnsol.androidtest.view.userDetail

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
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
import com.cdnsol.androidtest.model.response.UserDetailData
import com.cdnsol.androidtest.utils.AndroiddUtils
import com.cdnsol.androidtest.utils.SharedPreferenceUtil
import com.cdnsol.androidtest.view.base.BaseActivity
import com.cdnsol.androidtest.view.base.GlobalViewModelFactory
import com.cdnsol.androidtest.view.home.HomeViewModel
import com.cdnsol.androidtest.view.home.UsersListAdapter
import javax.inject.Inject

/*
* Activity for the user details
*
* */
class UserDetailActivity : BaseActivity() {
    @Inject
    lateinit var preferenceUtil: SharedPreferenceUtil
    var loginId: String? = null

    @Inject
    lateinit var factory: GlobalViewModelFactory<UserDetailViewModel>
    private lateinit var detailBinding: ActivityUserDetailBinding
    private val userDetailData: UserDetailData? = null

    //ViewModel
    val viewModel: UserDetailViewModel by lazy {
        ViewModelProvider(this, factory).get(UserDetailViewModel::class.java)
    }

    override val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .appComponent((application as TestApp).component).build()
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = DataBindingUtil.setContentView(this, R.layout.activity_user_detail)
        activityComponent.inject(this)
        detailBinding.viewModel = viewModel
        detailBinding.lifecycleOwner = this
        if (intent != null) {
            loginId = intent.getStringExtra("LOGIN");
            if (loginId != null)
                if (preferenceUtil.getUserDetailList().size != 0) {
                    Log.e("ListSize>>", "" + preferenceUtil.getUserDetailList().size)
                    Log.e("UserDetailList>>", "Not Null")
                    if (preferenceUtil.getUserDetailList().any { it!!.login == loginId }) {
                        Log.e("loginId>>", "Matched")
                        for (data in preferenceUtil.getUserDetailList()) {
                            if (data!!.login.equals(loginId)) {
                                Log.e("loginId>>", "Matched")
                                viewModel.useDetailData.value = data
                                break
                            }
                        }
                    } else {
                        Log.e("loginId>>", "Not Matched")
                        viewModel.getUserDetail(loginId!!)
                    }

                } else {
                    Log.e("UserDetailList>>", "Null")
                    viewModel.getUserDetail(loginId!!)
                }

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
        viewModel.useDetailData.observe(this, Observer { it ->
            it?.let {
                if (preferenceUtil.getUserDetailList().size != 0) {
                    if (!preferenceUtil.getUserDetailList().contains(it)) {
                        preferenceUtil.putUserDetailInList(it)
                    } else {
                        Log.e("Message>>", "Already Exist")
                    }
                } else {
                    preferenceUtil.putUserDetailInList(it)
                }
            }

        })
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