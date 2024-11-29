package com.terning.core.util

import java.util.regex.Pattern

private const val NAME_ERROR_PATTERN =
    "[!@#\$%^&*(),.?\":{}|<>\\[\\]\\\\/\\-=+~`\\p{S}\\p{P}]"
val NAME_ERROR_REGEX: Pattern =
    Pattern.compile(NAME_ERROR_PATTERN)
