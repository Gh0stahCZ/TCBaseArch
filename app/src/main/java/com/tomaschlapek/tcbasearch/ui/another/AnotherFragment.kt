package com.tomaschlapek.tcbasearch.ui.dash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.tomaschlapek.tcbasearch.R

class AnotherFragment : Fragment() {

  companion object {
    fun newInstance() = AnotherFragment()
  }

  private lateinit var viewModel: AnotherViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.another_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(AnotherViewModel::class.java)
  }



}
