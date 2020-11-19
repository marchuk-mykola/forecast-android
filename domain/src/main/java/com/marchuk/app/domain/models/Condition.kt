package com.marchuk.app.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Condition(
    val iconUrl: String,
    val text: String
) : Parcelable
