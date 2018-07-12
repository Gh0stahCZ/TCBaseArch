package com.tomaschlapek.tcbasearch.ui.dash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tomaschlapek.tcbasearch.DashActivity
import com.tomaschlapek.tcbasearch.R
import com.tomaschlapek.tcbasearch.util.toggleAlignment
import kotlinx.android.synthetic.main.dash_activity.*
import kotlinx.android.synthetic.main.dash_fragment.*

class DashFragment : Fragment() {

  companion object {
    fun newInstance() = DashFragment()
  }

//  private lateinit var viewModel: DashViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.dash_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
//    viewModel = ViewModelProviders.of(this).get(DashViewModel::class.java)

    material_button.setOnClickListener { activity?.bottom_appbar?.toggleAlignment() }
    message2.setOnClickListener { (activity as DashActivity).openAnotherFrag() }
  }

}
