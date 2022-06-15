package com.cdnsol.androidtest.view.home

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cdnsol.androidtest.R
import com.cdnsol.androidtest.databinding.ItemUsersListBinding
import com.cdnsol.androidtest.model.response.UserListData
import com.cdnsol.androidtest.view.userDetail.UserDetailActivity

class UsersListAdapter(
    var context: Context,
    private var userlist: ArrayList<UserListData>
) : RecyclerView.Adapter<UsersListAdapter.ViewHolder>() {
    //private lateinit var binding: ItemUsersListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.holderBinding.userListData = userlist[position]
        holder.holderBinding.container.setTag(R.id.id_position, position)
        holder.holderBinding.container.setTag(R.id.id_data, userlist[position].login)
        holder.holderBinding.container.setOnClickListener(View.OnClickListener { view ->
            val position: Int = view.getTag(R.id.id_position) as Int
            userlist[position].is_visited = true;
            val tag: String = view.getTag(R.id.id_data) as String
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("LOGIN", tag)
            (context as Activity).startActivityForResult(intent, 99)
        })
    }


    override fun getItemCount() = userlist.size

    inner class ViewHolder(binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {
        val holderBinding = binding
    }


    fun updateList(list: ArrayList<UserListData>) {
        userlist = list
        notifyDataSetChanged()
    }

}