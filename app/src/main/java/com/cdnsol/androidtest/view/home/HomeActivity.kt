package com.cdnsol.androidtest.view.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cdnsol.androidtest.R
import com.cdnsol.androidtest.TestApp
import com.cdnsol.androidtest.databinding.ActivityHomeBinding
import com.cdnsol.androidtest.di.component.ActivityComponent
import com.cdnsol.androidtest.di.component.DaggerActivityComponent
import com.cdnsol.androidtest.di.module.ActivityModule
import com.cdnsol.androidtest.model.response.UserListData
import com.cdnsol.androidtest.utils.AndroiddUtils
import com.cdnsol.androidtest.view.base.BaseActivity
import com.cdnsol.androidtest.view.base.GlobalViewModelFactory
import javax.inject.Inject


class HomeActivity : BaseActivity() {
    @Inject
    lateinit var factory: GlobalViewModelFactory<HomeViewModel>
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var adapter: UsersListAdapter

    //ViewModel
    val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this, factory).get(HomeViewModel::class.java)
    }

    override val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder().activityModule(ActivityModule(this))
            .appComponent((application as TestApp).component).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        activityComponent.inject(this)
        homeBinding.viewModel = viewModel
        initialviews()
        setObservers()
        searchUser()

    }

    fun initialviews() {
        homeBinding.rvUsers.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewModel.getUsersList()
    }

    fun setObservers() {
        viewModel.liveDataisLoading.observe(this, {
            it?.let {
                if (it) {
                    AndroiddUtils.showProgressDialog(this@HomeActivity)
                } else {
                    AndroiddUtils.hideProgressDialog(this@HomeActivity)
                }
            }
        })
        viewModel.userList.observe(this, {
            it?.let {
                adapter = UsersListAdapter(this, viewModel.userList.value!!)
                homeBinding.rvUsers.adapter = adapter
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

    private fun searchUser() {
        homeBinding.searchViewUsers.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filter(newText.toString())
                return true
            }
        })


    }

    fun filter(text: String?) {
        val temp: ArrayList<UserListData> = ArrayList()
        if(text != null && !text.isEmpty()) {
            for (d in viewModel.userList.value!!) {
                //or use .equal(text) with you want equal match
                //use .toLowerCase() for better matches
                if (d.login!!.lowercase().contains(text.lowercase())) {
                    temp.add(d)
                }
            }
            //update recyclerview
            adapter.updateList(temp)
        }else {
            adapter.updateList(viewModel.userList.value!!)
        }
    }
}