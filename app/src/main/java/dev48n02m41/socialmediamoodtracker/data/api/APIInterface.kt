package dev48n02m41.socialmediamoodtracker.data.api

import com.google.gson.GsonBuilder
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import java.util.concurrent.TimeUnit

interface APIInterface {

    @GET("api/")
    fun getAll(@Header("authorization") authHeader: String): Call<List<APIDiaryEntryEntity>>


    companion object {
        var BASE_URL = "https://social-mood-track-backend.herokuapp.com/v1/"

        private val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .build()

        fun create(): APIInterface {

            val gsonWithExclude = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gsonWithExclude))
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .build()
            return retrofit.create(APIInterface::class.java)
        }
    }
}