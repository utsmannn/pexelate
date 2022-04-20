package com.utsman.core.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    companion object : KoinComponent {
        private const val BASE_URL = "https://api.pexels.com/v1/"
        private const val AUTHORIZATION_KEY = "Authorization"
        private const val AUTHORIZATION_TOKEN = "563492ad6f91700001000001880a2e3eb5d6452a94a3dd050f6395a6"

        val get: RetrofitBuilder by inject()
    }

    fun build(): Retrofit {
        val gson = GsonBuilder()
            .setPrettyPrinting()
            .setLenient()
            .create()

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                chain.proceed(
                    chain.request()
                        .newBuilder()
                        .addHeader(AUTHORIZATION_KEY, AUTHORIZATION_TOKEN)
                        .build()
                )
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}