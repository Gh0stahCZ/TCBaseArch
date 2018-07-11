package com.tomaschlapek.tcbasearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.tomaschlapek.tcbasearch.ui.dash.AnotherFragment
import com.tomaschlapek.tcbasearch.ui.dash.DashFragment


class DashActivity : AppCompatActivity() {

//  @Inject
//  lateinit var anotherViewModelFactory: AnotherViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.dash_activity)
    if (savedInstanceState == null) {

      supportFragmentManager.transaction(now = true) {
        replace(R.id.container, DashFragment.newInstance())
      }
    }
  }

  fun openAnotherFrag() {
    val anotherFrag = AnotherFragment.newInstance()

    supportFragmentManager.transaction {
      setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
      addToBackStack(anotherFrag.tag)
      replace(R.id.container, AnotherFragment.newInstance())
    }
  }

}
