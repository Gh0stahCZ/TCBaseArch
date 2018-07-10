package com.tomaschlapek.tcbasearch.util

import com.google.android.material.bottomappbar.BottomAppBar
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