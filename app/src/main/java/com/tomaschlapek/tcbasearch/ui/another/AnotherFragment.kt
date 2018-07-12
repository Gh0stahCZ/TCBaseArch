package com.tomaschlapek.tcbasearch.ui.dash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.tomaschlapek.tcbasearch.DashActivity
import com.tomaschlapek.tcbasearch.R
import com.tomaschlapek.tcbasearch.data.CustomItem
import com.tomaschlapek.tcbasearch.ui.adapter.CustomViewAdapter
import com.tomaschlapek.tcbasearch.util.dp
import com.tomaschlapek.tcbasearch.widget.SpaceItemDecor
import kotlinx.android.synthetic.main.another_fragment.*

class AnotherFragment : Fragment() {

  companion object {
    fun newInstance() = AnotherFragment()
  }

  //  @Inject
  //  lateinit var anotherViewModelFactory: AnotherViewModelFactory

  private lateinit var customAdapter: CustomViewAdapter

  private lateinit var viewModel: AnotherViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.another_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    //    viewModel = ViewModelProviders.of(this).get(AnotherViewModel::class.java)
    viewModel = ViewModelProviders.of(this, (activity as DashActivity).anotherFragmentViewModelFactory).get(AnotherViewModel::class.java!!)
    init()
  }

  fun init() {

    val list = mutableListOf<CustomItem>()
    list.add(CustomItem("Jurrasic World", "2018", "22.6.2018", 4.5f, 346, "http://image.tmdb.org/t/p/w500/c9XxwwhPHdaImA2f1WEfEsbhaFB.jpg"))
    list.add(CustomItem("Dumb and Dumber", "2017", "2.5.2018", 3.2f, 36, "http://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"))
    list.add(CustomItem("Hello World", "2018", "15.2.2018", 3.5f, 56, "http://image.tmdb.org/t/p/w500/mabuNsGJgRuCTuGqjFkWe1xdu19.jpg"))
    list.add(CustomItem("Rampage", "2019", "18.9.2019", 2.1f, 906, "http://image.tmdb.org/t/p/w500/kqjL17yufvn9OVLyXYpvtyrFfak.jpg"))

    customAdapter = CustomViewAdapter(context!!, list) {
      (activity as DashActivity).openDetail(it)
    }

    view_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    view_list.addItemDecoration(SpaceItemDecor(48.dp))
    LinearSnapHelper().attachToRecyclerView(view_list)
    view_list.adapter = customAdapter
  }
}
