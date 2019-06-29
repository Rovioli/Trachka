package org.rovioli.trachka

data class UserData(
    val users: List<User>
)

data class User(
    val id: Int,
    val name: String
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
