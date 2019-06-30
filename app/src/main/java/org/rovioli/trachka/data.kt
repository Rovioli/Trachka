package org.rovioli.trachka

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("Data")
    val data: List<User>
)

data class User(
    val id: Int = 0,
    val name: String = ""
)

data class Data(
    val id: Int,
    val userid: Int,
    val dow: Int,
    val descr: String,
    val price: Int,
    val username: String,
    val downame: String
)
