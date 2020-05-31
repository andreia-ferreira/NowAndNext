package com.example.nowandnext.utils.binding

import android.widget.ImageView

import androidx.databinding.BindingAdapter

import com.bumptech.glide.Glide

object ImageViewBinding {

    @JvmStatic
    @BindingAdapter("android:insertFromUrl")
    fun setImageUrl(view: ImageView, url: String) {
        Glide.with(view.context)
                .asBitmap()
                .load(url)
                .thumbnail(0.3f)
                .into(view)
    }

}
