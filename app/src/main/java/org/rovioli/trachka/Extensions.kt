package org.rovioli.trachka

// TODO: replace this with a correct date
val week = arrayOf(
    "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"
)

fun getDayOfWeek(i: Int) = week[i - 1]
