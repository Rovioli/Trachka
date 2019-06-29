package org.rovioli.trachka

import kotlinx.coroutines.*

class ZhrachkaService(private val client: ZhrachkaClient) {
    suspend fun getUsers() = client.getUsers()
}