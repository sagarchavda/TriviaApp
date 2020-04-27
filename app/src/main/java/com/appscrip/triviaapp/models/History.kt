package com.appscrip.triviaapp.models

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

/**
 * Created by Sagar Chavda on 26/04/20.
 *
 * It's model for hold data of quiz history or store and get history data
 */
@Parcelize
@Entity
data class History(
    @PrimaryKey val id: Int = 0,
    val name: String,
    val dateTime: String,
    val answer1: String,
    val answer2: String
) : Parcelable
