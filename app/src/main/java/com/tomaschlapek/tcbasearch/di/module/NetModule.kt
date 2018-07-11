package com.tomaschlapek.tcbasearch.di.module

import android.content.Context
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import com.tomaschlapek.tcbasearch.R
import com.tomaschlapek.tcbasearch.util.CACHE_SIZE
import com.tomaschlapek.tcbasearch.util.JSON_DATE_FORMAT
import com.tomaschlapek.tcbasearch.util.NetInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule {

  @Provides
  @Singleton
  internal fun provideNetworkInterceptor(): NetInterceptor {
    return NetInterceptor()
  }

  @Provides
  @Singleton
  internal fun provideOkHttpClient(cache: Cache, networkInterceptor: NetInterceptor): OkHttpClient {
    return createApiClient(cache, networkInterceptor)
  }

  @Provides
  @Singleton
  fun provideRetrofit(context: Context, client: OkHttpClient,
    gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
      .baseUrl(context.resources.getString(R.string.base_url))
      .client(client)
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
      .addConverterFactory(gsonConverterFactory)
      .build()
  }

  @Provides
  @Singleton
  fun provideGson(): GsonConverterFactory {
    val gson = GsonBuilder()
      .setDateFormat(JSON_DATE_FORMAT)
      .create()
    return GsonConverterFactory.create(gson)
  }

  @Provides
  @Singleton
  fun providePicasso(context: Context): Picasso {

    return Picasso.get()
    //      .downloader(OkHttpDownloader(context))
    //      .listener { _, _, exception -> Timber.d("Exception " + exception.stackTrace) }
    //      .build()
  }

  @Provides
  @Singleton
  internal fun provideOkHttpCache(context: Context): Cache {
    return Cache(context.cacheDir, CACHE_SIZE.toLong())
  }


  /**
   * Creates OkHttp client with 10MiB cache.

   * @return Instance of OkHttp client.
   */
  private fun createApiClient(cache: Cache, interceptor: NetInterceptor): OkHttpClient {

    val builder = OkHttpClient.Builder()
    builder.addInterceptor(interceptor)
    builder.cache(cache)
    return builder.build()
  }
}