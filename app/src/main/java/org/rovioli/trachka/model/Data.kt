package org.rovioli.trachka.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int = 0,
    val name: String = ""
)

data class Spending(
    val id: Int,
    val userid: Int,
    val dow: Int,
    val descr: String,
    val price: Double,
    val username: String,
    val downame: String,
    @SerializedName("time_stampUnix")
    val timeStamp: Int,
    @SerializedName("time_stampISO8601")
    val timeStampIso: String,
    val cur: String
)
