package com.tomaschlapek.tcbasearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tomaschlapek.tcbasearch.ui.another.AnotherViewModelFactory
import com.tomaschlapek.tcbasearch.ui.dash.AnotherFragment
import com.tomaschlapek.tcbasearch.ui.dash.DashFragment
import com.tomaschlapek.tcbasearch.ui.detail.DetailFragment
import dagger.android.AndroidInjection
import javax.inject.Inject


class DashActivity : AppCompatActivity() {

  @Inject
  lateinit var anotherFragmentViewModelFactory: AnotherViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.dash_activity)

    AndroidInjection.inject(this) // useless here


    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
        .replace(R.id.container, DashFragment.newInstance())
        .commitNow()
    }
  }

  fun openDetail(detailId: String) {
    openDetailFrag()
  }

  fun openAnotherFrag() {
    val anotherFrag = AnotherFragment.newInstance()

    supportFragmentManager.beginTransaction()
      .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
      .replace(R.id.container, anotherFrag)
      .addToBackStack(anotherFrag.tag)
      .commit()
  }

  fun openDetailFrag() {
    val detailFrag = DetailFragment.newInstance()

    supportFragmentManager.beginTransaction()
      .replace(R.id.container, detailFrag)
      .addToBackStack(detailFrag.tag)
      .commit()
  }
}
