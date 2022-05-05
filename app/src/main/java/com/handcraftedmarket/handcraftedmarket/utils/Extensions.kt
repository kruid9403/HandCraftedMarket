package com.handcraftedmarket.handcraftedmarket.utils

import android.graphics.Color
import java.text.DecimalFormat

fun Int.isColorDark(): Boolean {
    val darkness: Double =
        1 - (0.299 * Color.red(this) + 0.587 * Color.green(this) + 0.114 * Color.blue(this)) / 255
    return darkness >= 0.5
}

fun Double.toStripe(): Int{
    return (this * 100).toInt()
}

fun Double.toDollarString(): String {
    val format = DecimalFormat("$#,###.00")
    format.isDecimalSeparatorAlwaysShown = true
    return format.format(this).toString()
}