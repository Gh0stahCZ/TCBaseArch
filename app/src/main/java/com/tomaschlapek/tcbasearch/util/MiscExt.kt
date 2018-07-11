package com.tomaschlapek.tcbasearch.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.LayoutRes
import com.google.android.material.bottomappbar.BottomAppBar
import com.squareup.picasso.Picasso
import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException

fun BottomAppBar.toggleAlignment() {
  val current = fabAlignmentMode
  fabAlignmentMode = current.xor(1)
}

fun RequestBody.bodyToString(): String {
  return try {
    val buffer = Buffer()
    writeTo(buffer)
    buffer.readUtf8()
  } catch (e: IOException) {
    "Do not work :/"
  }
}

val Int.dp: Int
  get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
  get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}

fun ImageView.loadUrl(url: String, placeHolderRes: Int? = null) {

  val requestMaker =
    Picasso.get()
      .load(url)
      .config(Bitmap.Config.RGB_565)

  placeHolderRes?.let {
    requestMaker.placeholder(it)
  }

  requestMaker.into(this)
}