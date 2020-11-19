package com.marchuk.app.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import timber.log.Timber

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(url)
        .transform(CenterInside())
        .into(this)
}