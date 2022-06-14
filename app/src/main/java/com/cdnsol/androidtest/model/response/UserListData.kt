package com.cdnsol.androidtest.model.response

import com.google.gson.annotations.SerializedName
import android.R
import android.widget.ImageView

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


class UserListData {
    @SerializedName("login")
    var login: String? = null
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("node_id")
    var nodeId: String? = null
    @SerializedName("avatar_url")
    var avatarUrl: String? = null
    @SerializedName("gravatar_id")
    var gravatarId: String? = null
    @SerializedName("url")
    var url: String? = null
    @SerializedName("html_url")
    var htmlUrl: String? = null
    @SerializedName("followers_url")
    var followersUrl: String? = null
    @SerializedName("following_url")
    var followingUrl: String? = null
    @SerializedName("gists_url")
    var gistsUrl: String? = null
    @SerializedName("starred_url")
    var starredUrl: String? = null
    @SerializedName("subscriptions_url")
    var subscriptionsUrl: String? = null
    @SerializedName("organizations_url")
    var organizationsUrl: String? = null
    @SerializedName("repos_url")
    var reposUrl: String? = null
    @SerializedName("events_url")
    var eventsUrl: String? = null
    @SerializedName("received_events_url")
    var receivedEventsUrl: String? = null
    @SerializedName("type")
    var type: String? = null
    @SerializedName("site_admin")
    var siteAdmin: Boolean? = null

   /* @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.getContext())
            .load(imageUrl)
            .into(view)

    }*/
}