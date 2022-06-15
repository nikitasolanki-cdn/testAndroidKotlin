package com.cdnsol.androidtest.di.module


import android.content.Context
import com.cdnsol.androidtest.BuildConfig
import com.cdnsol.androidtest.service.ApiInterface
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule(private val baseUrl: String) {
    @Provides
    @Singleton
    fun provideHttpClient(context: Context): OkHttpClient {
        val okhttpClientBuilder = OkHttpClient.Builder();
        okhttpClientBuilder.connectTimeout(2, TimeUnit.MINUTES);
        okhttpClientBuilder.readTimeout(2, TimeUnit.MINUTES);
        okhttpClientBuilder.writeTimeout(2, TimeUnit.MINUTES);

        //Logging Intercepter to print the logs in logcat
        if (BuildConfig.DEBUG) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            okhttpClientBuilder.addInterceptor(interceptor)
        }


        val client = okhttpClientBuilder.build()
        return client
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }
}