package com.appscrip.triviaapp.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics

/**
 * Created by Sagar Chavda on 02/04/20.
 * It's common class for perform convert px to dp or dp to px vice-versa
 */
class DimensionUtils(private val mContext: Context) {

    private val displayMetrics: DisplayMetrics
        private get() = resources.displayMetrics

    val screenWidth: Int
        get() = displayMetrics.widthPixels

    val screenHeight: Int
        get() = displayMetrics.heightPixels

    /**
     * It's accept px value and after convert it return in dp
     */
    fun px2dp(px: Float): Int {
        return (px / displayMetrics.density + 0.5f).toInt()
    }

    /**
     * It's accept dp value and after convert it return in px
     */
    fun dp2px(dp: Float): Int {
        return (dp * displayMetrics.density + 0.5f).toInt()
    }

    /**
     * It's accept px value and after convert it return in sp
     */
    fun px2sp(pxValue: Float): Int {
        return (pxValue / displayMetrics.scaledDensity + 0.5f).toInt()
    }

    /**
     * It's accept sp value and after convert it return in px
     */
    fun sp2px(spValue: Float): Int {
        return (spValue * displayMetrics.scaledDensity + 0.5f).toInt()
    }

    /**
     * It's return dimen pixel size
     */
    fun getDimensionPixelSize(id: Int): Int {
        return resources.getDimensionPixelSize(id)
    }

    private val resources: Resources
        get() {
            return mContext.resources
        }

}