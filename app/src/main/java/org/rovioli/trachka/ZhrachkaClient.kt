package org.rovioli.trachka

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ZhrachkaClient {
    @GET("zhrachka/home/getusers")
    suspend fun getUsers(): Response<Data<User>>

    @GET("zhrachka/home/getalldata")
    suspend fun getData(): Response<Data<Spending>>

    @GET("zhrachka/home/getaddspendings")
    suspend fun addSpending(
        @Query("userid") userid: Int?,
        @Query("dow") dow: Int?,
        @Query("descr") descr: String?,
        @Query("price") price: Int?
    ): Int?

    @GET("zhrachka/home/geteditspendings")
    suspend fun editSpending(
        @Query("userid") userid: Int?,
        @Query("dow") dow: Int?,
        @Query("descr") descr: String?,
        @Query("price") price: Int?,
        @Query("id") id: Int?
    ): Int?

    @GET("zhrachka/home/getdeletespendings")
    suspend fun deleteSpending(
        @Query("userid") userid: Int?,
        @Query("id") id: Int?
    ): Int?
}