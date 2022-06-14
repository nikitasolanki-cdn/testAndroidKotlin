package com.cdnsol.androidtest.view.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
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
    private lateinit var binding: ItemUsersListBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemUsersListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userListData = userlist.get(position)
        holder.tv_username.text = userListData.login
        holder.container.setTag(userListData.login)
        Glide.with(this.context)
            .load(userListData.avatarUrl)
            .into(holder.iv_user_img)
        holder.container.setOnClickListener(View.OnClickListener { view ->
            var tag : String = view.tag as String;
            val intent = Intent(context, UserDetailActivity::class.java)
            intent.putExtra("LOGIN", tag)
            context.startActivity(intent)
        })
    }


    override fun getItemCount(): Int {
        return userlist.size
    }

    inner class ViewHolder(binding: ItemUsersListBinding) : RecyclerView.ViewHolder(binding.root) {
        /*   fun bind(userListData: UserListData) {
   //             binding.userListData  = userListData
               binding.tvUserName.text = userListData.login
           }*/
        var tv_username: TextView
        var iv_user_img: ImageView
        var container : ConstraintLayout

        init {
            tv_username = itemView.findViewById(R.id.tv_user_name)
            iv_user_img = itemView.findViewById(R.id.iv_user_img)
            container =  itemView.findViewById(R.id.container)
        }

    }


    fun updateList(list: ArrayList<UserListData>) {
        userlist = list
        notifyDataSetChanged()
    }

}