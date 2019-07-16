package org.rovioli.trachka.model

import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("Data")
    val data: List<T>
)

data class User(
    val id: Int = 0,
    val name: String = ""
)

data class Spending(
    val id: Int,
    val userid: Int,
    val dow: Int,
    val descr: String,
    val price: Int,
    val username: String,
    val downame: String,
    @SerializedName("time_stampUnix")
    val timeStamp: Int,
    val cur: String
)
