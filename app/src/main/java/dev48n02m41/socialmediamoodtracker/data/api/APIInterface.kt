package dev48n02m41.socialmediamoodtracker.data.api

import com.google.gson.GsonBuilder
import dev48n02m41.socialmediamoodtracker.data.entities.APIDiaryEntryEntity
import dev48n02m41.socialmediamoodtracker.data.entities.DiaryEntryEntity
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface APIInterface {

    @GET("api/")
    suspend fun getAll(@Header("authorization") authHeader: String): Response<List<APIDiaryEntryEntity>>

    @POST("api/")
    suspend fun postALL(
        @Header("authorization") authHeader: String,
        @Body stuffToSend: List<DiaryEntryEntity>
    ): Response<ResponseBody>

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