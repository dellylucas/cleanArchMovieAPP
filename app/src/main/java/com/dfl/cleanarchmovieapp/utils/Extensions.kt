package com.dfl.cleanarchmovieapp.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide


fun ImageView.loadUrl(url: String) {
    Glide.with(context).load(url).into(this)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}