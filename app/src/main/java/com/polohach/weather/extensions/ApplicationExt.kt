package com.polohach.weather.extensions

import android.content.Context
import androidx.annotation.StringRes
import com.polohach.weather.App

fun dp2px(dp: Float) = (dp * App.instance.resources.displayMetrics.density).toInt()


/**
 * Get string from resources
 *
 * @param resId [Int]
 *
 * @return [String]
 */
fun getString(resId: Int, context: Context = App.instance): String = context.getString(resId)

/**
 * Get string from resources
 *
 * @param stringId [Int]
 *
 * @param formatArgs [Any]
 *
 * @return [String]
 */
fun getString(@StringRes stringId: Int,
              vararg formatArgs: Any,
              context: Context = App.instance): String = context.getString(stringId, *formatArgs)


