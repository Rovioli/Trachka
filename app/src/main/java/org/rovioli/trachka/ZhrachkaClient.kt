package org.rovioli.trachka

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ZhrachkaClient {
    @GET("/zhrachka/home/getusers")
    fun getUsers(): Call<List<User>>

    @GET("/zhrachka/home/getalldata")
    fun getData(): Call<List<Data>>

    @GET("/zhrachka/home/getaddspendings")
    fun addSpending(
        @Query("userid") userid: Int?,
        @Query("dow") dow: Int?,
        @Query("descr") descr: String?,
        @Query("price") price: Int?
    ): Int?

    @GET("/zhrachka/home/geteditspendings")
    fun editSpending(
        @Query("userid") userid: Int?,
        @Query("dow") dow: Int?,
        @Query("descr") descr: String?,
        @Query("price") price: Int?,
        @Query("id") id: Int?
    ): Int?

    @GET("/zhrachka/home/getdeletespendings")
    fun deleteSpending(
        @Query("userid") userid: Int?,
        @Query("id") id: Int?
    ): Int?
}