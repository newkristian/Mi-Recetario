package me.kristianconk.mirecetario.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    companion object {
        const val BASE_URL = "https://xjmmx2snf8.execute-api.us-west-2.amazonaws.com"

        fun create() =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client())
                .build()

        fun client(): OkHttpClient {
            val client = OkHttpClient()
            val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            val clientBuilder: OkHttpClient.Builder =
                client.newBuilder().addInterceptor(interceptor as HttpLoggingInterceptor)
            return clientBuilder.build()
        }
    }
}