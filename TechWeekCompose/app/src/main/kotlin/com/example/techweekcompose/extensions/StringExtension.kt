package com.example.techweekcompose.extensions

import android.net.Uri

private const val LIMIT = "limit"
private const val OFFSET = "offset"

fun String.getLimitAndOffsetValues() = with(Uri.parse(this)) {
    val limit = getQueryParameter(LIMIT)?.toInt() ?: 0
    val offset = getQueryParameter(OFFSET)?.toInt() ?: 0
    Pair(limit, offset)
}