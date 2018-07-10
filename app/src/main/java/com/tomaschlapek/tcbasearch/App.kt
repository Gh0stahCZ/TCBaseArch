package com.tomaschlapek.tcbasearch

import android.app.Activity
import android.app.Application
import android.app.Service
import com.tomaschlapek.tcbasearch.di.component.AppComponent
import com.tomaschlapek.tcbasearch.di.component.DaggerAppComponent
import com.tomaschlapek.tcbasearch.widget.DebugTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import timber.log.Timber
import javax.inject.Inject

class App : Application(), HasActivityInjector, HasServiceInjector {

  /* Public Constants *****************************************************************************/

  @Inject
  lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

  @Inject
  lateinit var mServiceInjector: DispatchingAndroidInjector<Service>

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

  override fun serviceInjector(): AndroidInjector<Service> {
    return mServiceInjector
  }

  companion object {

    @JvmStatic
    private lateinit var sAppComponent: AppComponent

    @JvmStatic
    fun getAppComponent(): AppComponent {
      return sAppComponent
    }
  }
}