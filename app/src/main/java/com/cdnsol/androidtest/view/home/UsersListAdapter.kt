package com.cdnsol.androidtest.view.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cdnsol.androidtest.databinding.ItemUsersListBinding
import com.cdnsol.androidtest.model.response.UserListData

class UsersListAdapter(
    var context: Context, private val homeViewModel: HomeViewModel) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    private lateinit var binding: ItemUsersListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: UserListData = homeViewModel.userList.value!![position]
        binding.userListData = data
        holder.itemView.setOnClickListener {
            Log.e("ItemView>>", "Clicked!!")
        }
    }


    override fun getItemCount(): Int {
        if (homeViewModel.userList.value == null)
            return 0;
        return homeViewModel.userList.value!!.size
    }

    inner class ViewHolder(binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {
    }


}