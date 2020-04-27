package com.appscrip.triviaapp.utils

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.util.Preconditions.checkArgument
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Sagar Chavda on 27/04/20.
 * It's class allow to get current date with specific format or change date format
 */
class DateUtil {
    companion object {

        /**
         * It's return current date and time with fix defined format as dd'th' MMMM HH:mm a
         * e.g. 23th March 02:45 pm
         */
        fun getCurrentDate(): String {
            val cal = Calendar.getInstance()
            val day = cal[Calendar.DATE]
            val newSdf =
                SimpleDateFormat(getDayOfMonthSuffix(day), Locale.getDefault())
            return try {
                    newSdf.format(Calendar.getInstance().time)
            } catch (e: ParseException) {
                e.printStackTrace()
                ""
            }
        }

        /**
         * It's return date suffix according to passed date
         */
        @SuppressLint("RestrictedApi")
        fun getDayOfMonthSuffix(n: Int): String {
            checkArgument(n in 1..31, "illegal day of month: $n")
            return if (n in 11..13) {
                "dd'th' MMMM HH:mm a"
            } else when (n % 10) {
                1 -> "dd'st' MMMM HH:mm a"
                2 -> "dd'nd' MMMM HH:mm a"
                3 -> "dd'rd' MMMM HH:mm a"
                else -> "dd'th' MMMM HH:mm a"
            }
        }
    }
}