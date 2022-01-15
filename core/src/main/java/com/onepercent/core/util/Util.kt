package com.onepercent.core.util

import java.text.DecimalFormat

fun Double.formatWeight(
    pattern: String = "#.##"
) : String {
    return DecimalFormat(pattern)
        .format(this)
        .replace(",", ".")
        .toDouble()
        .toString()
}