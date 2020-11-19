package com.marchuk.app

import android.os.Bundle
import androidx.navigation.NavController
import com.marchuk.app.domain.models.Location
import com.marchuk.app.feature_search.presentation.SearchNavigator

class AppNavigator : SearchNavigator {

    private var navController: NavController? = null

    override fun navigateToForecast(location: Location) {
        navController?.navigate(
            R.id.action_search_to_forecast,
            Bundle().apply {
                putParcelable("Location", location)
            }
        )
    }

    fun bind(navController: NavController) {
        this.navController = navController
    }

    fun unbind() {
        navController = null
    }

}