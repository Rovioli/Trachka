package org.rovioli.trachka

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ZhrachkaClient {
    @GET("zhrachka/home/users")
    suspend fun getUsers(): Response<List<User>>

    @GET("zhrachka/home/alldata")
    suspend fun getData(): Response<List<Spending>>

    @GET("zhrachka/home/addspendings")
    suspend fun addSpending(
        @Query("userid") userid: Int?,
        @Query("dow") dow: Int?,
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
}