package com.cdnsol.androidtest.view.home

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url: String?) {
    Glide.with(this.context)
        .load(url)
        .into(this)
}

@SuppressLint("ResourceAsColor")
@BindingAdapter("colorText")
fun TextView.colorText(color: Boolean?) {
    if (color == true) {
        this.setTextColor(resources.getColor(android.R.color.holo_blue_dark));
    } else {
        this.setTextColor(resources.getColor(android.R.color.black));
    }
}