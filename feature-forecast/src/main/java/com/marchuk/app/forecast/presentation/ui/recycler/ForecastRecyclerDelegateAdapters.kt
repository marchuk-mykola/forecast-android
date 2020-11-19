package com.marchuk.app.forecast.presentation.ui.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import com.marchuk.app.core.forecast.databinding.LayoutRecyclerBinding
import com.marchuk.app.core.forecast.databinding.RecyclerItemCurrentForecastBinding
import com.marchuk.app.core.forecast.databinding.RecyclerItemDailyForecastBinding
import com.marchuk.app.core.forecast.databinding.RecyclerItemHourlyForecastBinding
import com.marchuk.app.core.utils.loadImage
import com.marchuk.app.core.utils.recycler.DelegateAdapterItem
import com.marchuk.app.forecast.presentation.models.*
import kotlin.math.roundToInt

@SuppressLint("SetTextI18n")
object ForecastRecyclerDelegateAdapters {

    internal fun hourlyForecasts() =
        adapterDelegateViewBinding<HourlyRecyclerForecastsHolder, DelegateAdapterItem, LayoutRecyclerBinding>(
            { layoutInflater, root -> LayoutRecyclerBinding.inflate(layoutInflater, root, false) }
        ) {

            val delegationAdapter = ListDelegationAdapter(hourlyForecast())

            with(binding.recycler) {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = delegationAdapter
            }

            bind {
                delegationAdapter.items = item.list
                delegationAdapter.notifyDataSetChanged()
            }

        }

    internal fun dailyForecasts() =
        adapterDelegateViewBinding<DailyRecyclerForecastsHolder, DelegateAdapterItem, LayoutRecyclerBinding>(
            { layoutInflater, root -> LayoutRecyclerBinding.inflate(layoutInflater, root, false) }
        ) {

            val delegationAdapter = ListDelegationAdapter(dailyForecast())

            with(binding.recycler) {
                layoutManager = LinearLayoutManager(context)
                adapter = delegationAdapter
            }

            bind {
                delegationAdapter.items = item.list
                delegationAdapter.notifyDataSetChanged()
            }

        }

    internal fun currentForecast() =
        adapterDelegateViewBinding<CurrentForecastRecyclerModel, DelegateAdapterItem, RecyclerItemCurrentForecastBinding>(
            { layoutInflater, root -> RecyclerItemCurrentForecastBinding.inflate(layoutInflater, root, false) }
        ) {

            bind {
                with(binding) {
                    currentDayTextView.text = item.currentDateFormatted
                    cityNameTextView.text = item.cityName
                    currentTemperatureTextView.text = "${item.tempC.roundToInt()}°C"
                    val temperature = item.tempC.roundToInt()
                    val textColor = if (temperature < 10) {
                        getColor(com.marchuk.app.core.R.color.blue)
                    } else if (temperature in 10..20) {
                        getColor(com.marchuk.app.core.R.color.dark)
                    } else {
                        getColor(com.marchuk.app.core.R.color.red)
                    }

                    currentTemperatureTextView.setTextColor(textColor)

                    maxTemperatureTextView.text = "${item.maxTemp.roundToInt()}°"
                    minTemperatureTextView.text = "${item.minTemp.roundToInt()}°"
                    conditionTextView.text = "${item.condition.text}"
                    feelsLikeTextView.text = "Feels like ${item.feelsLikeC.roundToInt()}°"
                    conditionIcon.loadImage("https:${item.condition.iconUrl}")
                }
            }
        }

    private fun hourlyForecast() =
        adapterDelegateViewBinding<HourlyForecastRecyclerModel, DelegateAdapterItem, RecyclerItemHourlyForecastBinding>(
            { layoutInflater, root -> RecyclerItemHourlyForecastBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                with(binding) {
                    hourNameTextView.text = item.currentTimeFormatted
                    temperatureTextView.text = "${item.tempC.roundToInt()}°"
                }
            }
        }

    private fun dailyForecast() =
        adapterDelegateViewBinding<DailyForecastRecyclerModel, DelegateAdapterItem, RecyclerItemDailyForecastBinding>(
            { layoutInflater, root -> RecyclerItemDailyForecastBinding.inflate(layoutInflater, root, false) }
        ) {
            bind {
                with(binding) {
                    dayNameTextView.text = item.currentDateFormatted
                    conditionIcon.loadImage("https:${item.condition.iconUrl}")
                    maxTemperatureTextView.text = "${item.maxTempC.roundToInt()}°"
                    minTemperatureTextView.text = "${item.minTempC.roundToInt()}°"
                }
            }
        }

}