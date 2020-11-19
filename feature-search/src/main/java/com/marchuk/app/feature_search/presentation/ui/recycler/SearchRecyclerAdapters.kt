package com.marchuk.app.feature_search.presentation.ui.recycler

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.marchuk.app.core.feature_search.databinding.RecyclerItemFoundedLocationBinding
import com.marchuk.app.core.feature_search.databinding.RecyclerItemRememberedLocationBinding
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.Location
import com.marchuk.app.feature_search.presentation.models.FoundedLocation
import com.marchuk.app.feature_search.presentation.models.RememberedLocation

internal object SearchRecyclerAdapters {

    internal fun rememberedLocationDelegateAdapter(
        onRememberedLocationClick: (Location) -> Unit,
        onDeleteLocationClick: (Location) -> Unit
    ) = adapterDelegateViewBinding<RememberedLocation, DelegateAdapterItem, RecyclerItemRememberedLocationBinding>(
        { layoutInflater, root -> RecyclerItemRememberedLocationBinding.inflate(layoutInflater, root, false) }
    ) {

        with(binding) {
            root.setOnClickListener {
                onRememberedLocationClick(item.location)
            }
            buttonDelete.setOnClickListener {
                onDeleteLocationClick(item.location)
            }
        }

        bind {
            with(binding) {
                cityNameTextView.text = item.location.name
            }
        }

    }

    internal fun foundedLocationDelegateAdapter(
        onFoundedLocationClick: (Location) -> Unit
    ) = adapterDelegateViewBinding<FoundedLocation, DelegateAdapterItem, RecyclerItemFoundedLocationBinding>(
        { layoutInflater, root -> RecyclerItemFoundedLocationBinding.inflate(layoutInflater, root, false) }
    ) {

        with(binding) {
            cityNameTextView.setOnClickListener {
                onFoundedLocationClick(item.location)
            }
        }

        bind {
            with(binding) {
                cityNameTextView.text = item.location.name
            }
        }

    }

}