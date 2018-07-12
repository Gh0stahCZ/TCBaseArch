package com.tomaschlapek.tcbasearch.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomaschlapek.tcbasearch.R
import com.tomaschlapek.tcbasearch.data.CustomItem
import com.tomaschlapek.tcbasearch.util.inflate
import com.tomaschlapek.tcbasearch.util.loadUrl
import kotlinx.android.synthetic.main.view_item.view.*
import timber.log.Timber


class CustomViewAdapter(context: Context, itemList: MutableList<CustomItem>, val detailListener: (String) -> Unit) : BaseAdapter<CustomItem>(context, itemList) {

  override fun createCustomViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    val inflatedView = parent.inflate(R.layout.view_item, false)
    return CustomView(inflatedView)
  }

  override fun onBindData(holder: RecyclerView.ViewHolder, value: CustomItem) {
    when (holder) {
      is CustomView -> {
        holder.bind(value)
      }
    }
  }

  override fun onItemViewType(pos: Int) = 0

  override fun countItems() = mItemList.size


  inner class CustomView(val view: View) : RecyclerView.ViewHolder(view) {

    fun bind(item: CustomItem) {
      view.view_detail_title.text = item.title
      view.view_detail_lang.text = item.lang
      view.view_detail_like_avg.text = view.context.getString(R.string.view_counter, item.avgRate)
      view.view_detail_like_count.text = item.rateCnt.toString()
      //      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //        view.imageView.clipToOutline = true
      //      }
      view.imageView.loadUrl(item.imageUrl)

      view.setOnClickListener {
        Timber.i("CLicked ...")
        detailListener.invoke(item.title)
      }
    }


  }

}