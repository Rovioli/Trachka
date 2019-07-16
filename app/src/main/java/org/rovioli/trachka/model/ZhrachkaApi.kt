package org.rovioli.trachka.model

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ZhrachkaApi {
    @GET("zhrachka/home/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("zhrachka/home/alldata")
    suspend fun getData(): Response<List<Spending>>

    @GET("zhrachka/home/alldata")
    suspend fun getData(
        @Query("userid") userid: Int?
    ): Response<List<Spending>>

    @GET("zhrachka/home/addspendings")
    suspend fun addSpending(
        @Query("userid") userid: Int?,
        @Query("time_stamp") time: Long?,
        @Query("descr") descr: String?,
        @Query("price") price: Int?
    ): Int?

    @GET("zhrachka/home/editspendings")
    suspend fun editSpending(
        @Query("userid") userid: Int?,
        @Query("dow") dow: Int?,
        @Query("descr") descr: String?,
        @Query("price") price: Int?,
        @Query("id") id: Int?,
        @Query("time_stamp") timeStamp: Int?
    ): Int?

    @GET("zhrachka/home/deletespendings")
    suspend fun deleteSpending(
        @Query("userid") userid: Int?,
        @Query("id") id: Int?
    ): Int?

    companion object {
        val CLIENT: ZhrachkaApi = Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ZhrachkaApi::class.java)
    }
}