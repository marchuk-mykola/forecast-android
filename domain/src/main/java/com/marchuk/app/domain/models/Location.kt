package com.marchuk.app.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
      val id: Int = 0,
      val name: String,
      val region: String,
      val country: String,
      val lat: Double,
      val lon: Double
) : Parcelable