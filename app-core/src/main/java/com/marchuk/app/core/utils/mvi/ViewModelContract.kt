package com.marchuk.app.core.utils.mvi

/**
 * Internal Contract to be implemented by ViewModel
 * Required to intercept and log ViewEvents
 */
internal interface ViewModelContract<ACTION> {
    fun process(viewAction: ACTION)
}