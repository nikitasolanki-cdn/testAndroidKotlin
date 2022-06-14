package com.cdnsol.androidtest.view.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cdnsol.androidtest.databinding.ItemUsersListBinding
import com.cdnsol.androidtest.model.response.UserListData

class UsersListAdapter(
    var context: Context,
    private val arrayList: ArrayList<UserListData>
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    private lateinit var binding: ItemUsersListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: UserListData = arrayList[position]
        holder.itemView.setOnClickListener {
            Log.e("ItemView>>","Clicked!!")
        }
        holder.bind(data)

    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class ViewHolder(binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(userListData: UserListData) {
            with(userListData) {
                Glide.with(context)
                    .load(userListData.avatarUrl)
                    .into(binding.ivUserImg)
            }
        }
    }


}