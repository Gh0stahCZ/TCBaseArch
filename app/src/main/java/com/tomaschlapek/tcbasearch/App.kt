package com.tomaschlapek.tcbasearch

import android.app.Activity
import android.app.Application
import com.tomaschlapek.tcbasearch.di.component.AppComponent
import com.tomaschlapek.tcbasearch.di.component.DaggerAppComponent
import com.tomaschlapek.tcbasearch.widget.DebugTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector /*, HasFragmentInjector */{

  /* Public Constants *****************************************************************************/

  @Inject
  lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

  // TODO Change to androidx fragment when library will be ready
  //  @Inject
  //  lateinit var mFragmentInjector: DispatchingAndroidInjector<android.app.Fragment>

  override fun onCreate() {
    super.onCreate()

    // Start monitoring crashes.
    if (BuildConfig.DEBUG_MODE) {
      Timber.plant(DebugTree())
    } else {
      // TODO Uncomment after Crashlytics addition
      //  Fabric.with(this, new Crashlytics());
      //  Timber.plant(new KrashlyticsTree());
      Timber.plant(DebugTree())
    }

    sAppComponent = DaggerAppComponent.builder()
      .application(this)
      .build()

    sAppComponent.inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return mActivityInjector
  }

  // TODO Change to androidx fragment when library will be ready
  //  override fun fragmentInjector(): AndroidInjector<android.app.Fragment> {
  //    return mFragmentInjector
  //  }

  lateinit var sAppComponent: AppComponent
    private set

}