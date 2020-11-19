package com.marchuk.app.feature_search.presentation

import android.os.Parcelable
import com.marchuk.app.domain.models.Location
import com.marchuk.app.feature_search.presentation.models.FoundedLocation
import com.marchuk.app.feature_search.presentation.models.RememberedLocation
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchViewState(
    val state: SearchState,
    val foundedLocations: List<FoundedLocation>? = null,
    val rememberedLocations: List<RememberedLocation> = emptyList(),
    val input: String = String()
) : Parcelable

sealed class SearchViewEffect {
    data class ShowError(val error: String) : SearchViewEffect()
}

sealed class SearchViewAction {
    data class OnInputChanged(val input: String) : SearchViewAction()
    data class OnLocationClicked(val location: Location) : SearchViewAction()
    data class OnDeleteRememberedLocationClicked(val location: Location) : SearchViewAction()
}

sealed class SearchState : Parcelable {

    @Parcelize
    object ShowSuggestions : SearchState(), Parcelable

    @Parcelize
    object Searching : SearchState(), Parcelable

    @Parcelize
    object Success : SearchState(), Parcelable

    @Parcelize
    object EmptySuccess : SearchState(), Parcelable

    internal sealed class Error : SearchState(), Parcelable {
        @Parcelize
        object NetworkError : Error(), Parcelable

        @Parcelize
        object ApiError : Error(), Parcelable

        @Parcelize
        object ValidationError : Error(), Parcelable

        @Parcelize
        object UnknownError : Error(), Parcelable
    }

}