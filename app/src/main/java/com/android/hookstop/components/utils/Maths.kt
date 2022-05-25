package com.android.hookstop.components.utils


/**
 * Returns a value between [minValue] and [maxValue]
 * @receiver Float
 * @param value Float
 * @param minValue Float
 * @param maxValue Float
 * @return Float
 */
    fun Float.clamp(minValue: Float,
                    maxValue: Float): Float = minValue
        .coerceAtLeast(maxValue
            .coerceAtMost(this))