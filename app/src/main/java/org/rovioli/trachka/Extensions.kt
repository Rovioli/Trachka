package org.rovioli.trachka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import java.util.concurrent.TimeUnit

// TODO: replace this with a correct date
val week = arrayOf(
    "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
)

fun getDayOfWeek(i: Int) = week[i - 1]

fun currentTimeSeconds(): Long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())

fun View.getString(id: Int): String = this.context.getString(id)

fun ViewGroup.inflate(id: Int): View = LayoutInflater.from(this.context).inflate(id, this)
