package com.dfl.cleanarchmovieapp.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun ImageView.setImageUri(imageUri: String?) {
    imageUri?.let { loadUrl(com.dfl.sharedmodule.Constants.BASE_URL_MOVIES_DB_IMAGE_HIGH + it) }
}