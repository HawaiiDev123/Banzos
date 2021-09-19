package com.mns.banzosapp.custom_views

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mns.banzosapp.R

class ChicWikProgressBar(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.progress_bar_design)
        setCancelable(false)
        val imageView = findViewById<ImageView>(R.id.imageViewProgress)
        Glide.with(context).load(R.drawable.loading_banzos).into(imageView)
        if (window != null) window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}