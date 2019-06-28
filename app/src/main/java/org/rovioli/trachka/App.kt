package org.rovioli.trachka

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    lateinit var service: ZhrachkaService

    override fun onCreate() {
        super.onCreate()
        service = ZhrachkaService(
            Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ZhrachkaClient::class.java)
        )
    }
}