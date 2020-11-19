package com.marchuk.app.feature_search.presentation.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.marchuk.app.core.feature_search.R
import com.marchuk.app.core.feature_search.databinding.FragmentSearchBinding
import com.marchuk.app.core.utils.addDividerDecorator16dp
import com.marchuk.app.core.utils.gone
import com.marchuk.app.core.utils.mvi.MviFragment
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.core.utils.visible
import com.marchuk.app.feature_search.presentation.*
import com.marchuk.app.feature_search.presentation.ui.recycler.SearchRecyclerAdapters
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

class SearchFragment :
    MviFragment<FragmentSearchBinding, SearchViewState, SearchViewEffect, SearchViewAction, SearchViewModel>(R.layout.fragment_search) {

    companion object {
        private const val STATE_KEY = "SearchViewState"
    }

    override lateinit var viewModel: SearchViewModel
    private lateinit var delegationAdapter: ListDelegationAdapter<List<DelegateAdapterItem>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun renderViewState(viewState: SearchViewState) {
        when (viewState.state) {
            SearchState.ShowSuggestions -> {
                delegationAdapter.items = viewState.rememberedLocations
                delegationAdapter.notifyDataSetChanged()
                if (viewState.rememberedLocations.isEmpty()) {
                    showUserHintOrError(viewState.state)
                } else {
                    hideError()
                }
            }
            SearchState.Searching -> {
                hideError()
            }
            SearchState.Success -> {
                delegationAdapter.items = viewState.foundedLocations
                delegationAdapter.notifyDataSetChanged()
                hideError()
            }
            SearchState.EmptySuccess -> {
                delegationAdapter.items = viewState.foundedLocations
                delegationAdapter.notifyDataSetChanged()
                showUserHintOrError(viewState.state)
            }
            else -> showUserHintOrError(viewState.state)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::viewModel.isInitialized)
            outState.putParcelable(STATE_KEY, viewModel.viewStates().value)
    }

    override fun renderViewEffect(viewEffect: SearchViewEffect) {
        when (viewEffect) {
            is SearchViewEffect.ShowError -> Timber.w(viewEffect.error)
        }
    }

    override fun setupUi(savedInstanceState: Bundle?) {
        viewModel = getViewModel {
            parametersOf(savedInstanceState?.getParcelable<SearchViewState>(STATE_KEY))
        }

        delegationAdapter = ListDelegationAdapter(
            SearchRecyclerAdapters.foundedLocationDelegateAdapter { location ->
                binding.searchView.clearFocus()
                processAction(SearchViewAction.OnLocationClicked(location))
            },
            SearchRecyclerAdapters.rememberedLocationDelegateAdapter(
                onRememberedLocationClick = { location ->
                    binding.searchView.clearFocus()
                    processAction(SearchViewAction.OnLocationClicked(location))
                },
                onDeleteLocationClick = { location ->
                    processAction(SearchViewAction.OnDeleteRememberedLocationClicked(location))
                }
            )
        )

        with(binding) {
            with(recycler) {
                adapter = delegationAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addDividerDecorator16dp()
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    searchView.clearFocus()
                    processAction(SearchViewAction.OnInputChanged(query ?: String()))
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    processAction(SearchViewAction.OnInputChanged(newText ?: String()))
                    return true
                }
            })
        }
    }

    private fun showUserHintOrError(state: SearchState) {
        delegationAdapter.items = emptyList()
        delegationAdapter.notifyDataSetChanged()
        with(binding) {
            when (state) {
                SearchState.ShowSuggestions -> {
                    errorTextView.text = getString(R.string.find_locations_hint)
                    errorTextView.setTopDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_location))
                }
                SearchState.EmptySuccess -> {
                    errorTextView.text = getString(R.string.no_locations_found)
                    errorTextView.setTopDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_location))
                }
                SearchState.Error.NetworkError -> {
                    errorTextView.text = getString(R.string.error_network)
                    errorTextView.setTopDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_connection_error
                        )
                    )
                }
                else -> {
                    errorTextView.text = getString(R.string.unknown_error_occured)
                    errorTextView.setTopDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_alert))
                }
            }
            errorTextView.visible()
        }
    }

    private fun hideError() {
        with(binding) {
            errorTextView.gone()
        }
    }

    private fun TextView.setTopDrawable(drawable: Drawable?) {
        this.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
    }

}