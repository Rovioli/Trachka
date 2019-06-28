package org.rovioli.trachka

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class ZhrachkaService(val client: ZhrachkaClient) {
    // TODO: coroutines
    fun getUsers() =  client.getUsers().execute().body()
}