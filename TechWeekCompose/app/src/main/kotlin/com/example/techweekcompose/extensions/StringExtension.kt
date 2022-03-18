package com.example.techweekcompose.extensions

import android.net.Uri
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

private const val LIMIT = "limit"
private const val OFFSET = "offset"

fun String.getLimitAndOffsetValues() = with(Uri.parse(this)) {
    val limit = getQueryParameter(LIMIT)?.toInt() ?: 0
    val offset = getQueryParameter(OFFSET)?.toInt() ?: 0
    Pair(limit, offset)
}

fun String.encode(): String = URLEncoder.encode(this, StandardCharsets.UTF_8.toString())