package com.marchuk.app.forecast.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.marchuk.app.core.forecast.R
import com.marchuk.app.core.forecast.databinding.FragmentForecastBinding
import com.marchuk.app.core.utils.addDividerDecorator24dp
import com.marchuk.app.core.utils.mvi.MviFragment
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.domain.models.Location
import com.marchuk.app.forecast.presentation.ui.recycler.ForecastRecyclerDelegateAdapters
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

internal class ForecastFragment : MviFragment<FragmentForecastBinding, ForecastViewState, ForecastViewEffect,
      ForecastViewAction, ForecastViewModel>(R.layout.fragment_forecast) {

    companion object {
        private const val STATE_KEY = "SearchViewState"
        private const val LOCATION = "Location"
    }

    override lateinit var viewModel: ForecastViewModel
    private lateinit var delegationAdapter: ListDelegationAdapter<List<DelegateAdapterItem>>
    private val location: Location get() = requireArguments().getParcelable(LOCATION)!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (::viewModel.isInitialized)
            outState.putParcelable(STATE_KEY, viewModel.viewStates().value)
    }

    override fun renderViewState(viewState: ForecastViewState) {
        when (viewState.state) {
            ForecastState.Loading -> {
                binding.swipeRefreshLayout.isRefreshing = true
            }
            ForecastState.Loaded -> {
                binding.swipeRefreshLayout.isRefreshing = false
                viewState.forecastItem?.currentForecast?.let {
                    binding.root.background =
                        if (it.isDay) {
                            ContextCompat.getDrawable(requireContext(), R.drawable.day_gradient)
                        } else {
                            ContextCompat.getDrawable(requireContext(), R.drawable.night_gradient)
                        }
                }

                delegationAdapter.items = viewState.recyclerList
                delegationAdapter.notifyDataSetChanged()
            }
            ForecastState.Error.NetworkError -> {
                binding.swipeRefreshLayout.isRefreshing = false
            }
            ForecastState.Error.UnknownError -> {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    override fun renderViewEffect(viewEffect: ForecastViewEffect) =
        when (viewEffect) {
            ForecastViewEffect.ShowToast -> showToast()
        }

    override fun setupUi(savedInstanceState: Bundle?) {
        viewModel = getViewModel {
            parametersOf(
                savedInstanceState?.getParcelable<ForecastViewState>(STATE_KEY),
                location
            )
        }

        delegationAdapter = ListDelegationAdapter(
            ForecastRecyclerDelegateAdapters.currentForecast(),
            ForecastRecyclerDelegateAdapters.hourlyForecasts(),
            ForecastRecyclerDelegateAdapters.dailyForecasts()
        )

        with(binding) {
            with(recycler) {
                adapter = delegationAdapter
                layoutManager = LinearLayoutManager(requireContext())
                addDividerDecorator24dp()
            }
            swipeRefreshLayout.setOnRefreshListener {
                processAction(ForecastViewAction.ReloadData)
            }
        }
    }

    private fun showToast() {
        Toast.makeText(requireContext(), R.string.unknown_error_occured, Toast.LENGTH_LONG).show()
    }

}