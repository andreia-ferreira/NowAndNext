package com.example.nowandnext.network

import android.app.Application
import com.example.nowandnext.BuildConfig
import com.example.nowandnext.model.networkModel.ChannelProgrammingResponse
import com.example.nowandnext.model.networkModel.ChannelsResponse

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper(val mApplication: Application) {

    suspend fun getChannels(): Response<ChannelsResponse?> {
        return getClient(BuildConfig.BASE_URL).getChannels(AGENT, FILTER, ORDER_BY_CHANNEL, INLINE_COUNT)
    }

    suspend fun getMoreChannels(nextUrl: String): Response<ChannelsResponse?> {
        return getClient(BuildConfig.BASE_URL).getMoreChannels(nextUrl)
    }

    suspend fun getChannelProgramming(callLetter: String): Response<ChannelProgrammingResponse?> {
        val filter = "CallLetter%20eq%20%27$callLetter%27"
        return getClient(BuildConfig.BASE_URL).getChannelProgramming(AGENT, filter, ORDER_BY_DATE)
    }

    private fun getClient(url: String): IRetrofitClient {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .build()
                    chain.proceed(newRequest)
                }
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()


            return retrofit.create(IRetrofitClient::class.java)

        }

    companion object {
        private val TAG = RetrofitHelper::class.java.simpleName
        const val AGENT = "AND"
        const val FILTER = "substringof(%27MEO_Mobile%27,AvailableOnChannels)%20and%20IsAdult%20eq%20false"
        const val ORDER_BY_CHANNEL = "ChannelPosition%20asc"
        const val ORDER_BY_DATE = "StartDate%20asc"
        const val INLINE_COUNT = "allpages"

        // For Singleton instantiation
        @Volatile
        private var instance: RetrofitHelper? = null

        fun getInstance(application: Application) =
            instance ?: synchronized(this) {
                instance ?: RetrofitHelper(application).also { instance = it }
            }
    }

}
