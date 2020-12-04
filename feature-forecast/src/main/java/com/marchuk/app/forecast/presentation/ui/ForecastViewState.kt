package com.marchuk.app.forecast.presentation.ui

import android.os.Parcelable
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.ForecastItem
import com.marchuk.app.domain.models.Location
import kotlinx.android.parcel.Parcelize

@Parcelize
internal data class ForecastViewState(
    val state: ForecastState,
    val location: Location,
    val forecastItem: ForecastItem? = null,
    val recyclerList: List<DelegateAdapterItem>? = null
) : Parcelable

internal sealed class ForecastViewEffect {
   object ShowToast : ForecastViewEffect()
}

internal sealed class ForecastViewAction {
    object ReloadData : ForecastViewAction()
}

internal sealed class ForecastState : Parcelable {

    @Parcelize
    object Loading : ForecastState(), Parcelable

    @Parcelize
    object Loaded : ForecastState(), Parcelable

    internal sealed class Error : Parcelable, ForecastState() {

        @Parcelize
        object NetworkError : Error(), Parcelable

        @Parcelize
        object UnknownError : Error(), Parcelable

    }


}