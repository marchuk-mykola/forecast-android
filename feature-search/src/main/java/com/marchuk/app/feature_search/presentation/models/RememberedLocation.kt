package com.marchuk.app.feature_search.presentation.models

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.Location
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RememberedLocation(val location: Location) : DelegateAdapterItem, Parcelable
