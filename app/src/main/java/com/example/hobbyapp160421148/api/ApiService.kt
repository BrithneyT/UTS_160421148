package com.example.hobbyapp160421148.api



import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @FormUrlEncoded
    @POST("api-login.php")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("api-register.php")
    fun register(
        @Field("username") username: String,
        @Field("namaDepan") firstName: String,
        @Field("namaBelakang") lastName: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @FormUrlEncoded
    @POST("api-profile.php")
    fun updateProfile(
        @Field("id") userId: Int,
        @Field("namaDepan") firstName: String,
        @Field("namaBelakang") lastName: String,
        @Field("password") password: String
    ): Call<UserProfileResponse>

    @GET("api-get-user-profile.php")
    fun getUserProfile(
        @Query("id") userId: Int
    ): Call<UserProfileResponse>


}