package com.tomaschlapek.tcbasearch.di.module

import com.tomaschlapek.tcbasearch.DashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

  @ContributesAndroidInjector
  abstract fun contributeDashActivityInjector(): DashActivity

  // TODO Uncomment when library will be ready for androidx fragment
  //  @ContributesAndroidInjector
  //  abstract fun contributeAnotherFragmentActivityInjector(): AnotherFragment

}