package com.satyayudha0077.assessment_mobpro.network

import com.satyayudha0077.assessment_mobpro.BuildConfig
import com.satyayudha0077.assessment_mobpro.model.Buah
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import retrofit2.http.PUT
import retrofit2.http.Path

private const val BASE_URL =
    "https://qhtiblzrsthedprjzcse.supabase.co/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .build()

interface BuahApiService {

    @GET("rest/v1/buah?select=*")
    suspend fun getFruits(
        @Header("apikey")
        apiKey: String = BuildConfig.SUPABASE_KEY,

        @Header("Authorization")
        token: String = "Bearer ${BuildConfig.SUPABASE_KEY}"
    ): List<Buah>

    @POST("rest/v1/buah")
    suspend fun postBuah(
        @Header("apikey")
        apiKey: String = BuildConfig.SUPABASE_KEY,

        @Header("Authorization")
        token: String = "Bearer ${BuildConfig.SUPABASE_KEY}",

        @Header("Prefer")
        prefer: String = "return=minimal",

        @Body
        buah: Buah
    )

    @DELETE("rest/v1/buah")
    suspend fun deleteBuah(
        @Header("apikey")
        apiKey: String = BuildConfig.SUPABASE_KEY,

        @Header("Authorization")
        token: String = "Bearer ${BuildConfig.SUPABASE_KEY}",

        @Query("id")
        id: String
    )

    @PATCH("rest/v1/buah")
    suspend fun updateBuah(
        @Header("apikey")
        apiKey: String = BuildConfig.SUPABASE_KEY,

        @Header("Authorization")
        token: String = "Bearer ${BuildConfig.SUPABASE_KEY}",

        @Header("Prefer")
        prefer: String = "return=minimal",

        @Query("id")
        id: String,

        @Body
        buah: Buah
    )

    @PUT("storage/v1/object/foto-buah/{fileName}")
    @Headers(
        "x-upsert:true",
        "Content-Type:image/png"
    )
    suspend fun uploadImage(
        @Path("fileName") fileName: String,

        @Header("apikey")
        apiKey: String = BuildConfig.SUPABASE_KEY,

        @Header("Authorization")
        token: String = "Bearer ${BuildConfig.SUPABASE_KEY}",

        @Body
        body: RequestBody
    )
}

object BuahApi {
    val service: BuahApiService by lazy {
        retrofit.create(BuahApiService::class.java)
    }
}

enum class ApiStatus {
    LOADING,
    SUCCESS,
    FAILED
}