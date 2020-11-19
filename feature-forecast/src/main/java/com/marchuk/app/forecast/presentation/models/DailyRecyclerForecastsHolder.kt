package com.marchuk.app.forecast.presentation.models

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DailyRecyclerForecastsHolder(
    val list: List<DailyForecastRecyclerModel>
) : DelegateAdapterItem, Parcelable