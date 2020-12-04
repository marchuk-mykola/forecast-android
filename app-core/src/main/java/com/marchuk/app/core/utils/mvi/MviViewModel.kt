package com.marchuk.app.core.utils.mvi

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.marchuk.app.core.utils.SingleLiveEvent
import com.marchuk.app.core.utils.TAG
import timber.log.Timber

/**
 * Create ViewModels by Extending this class.
 *
 * @param STATE ViewState should represent the current state of the view at any given time.
 * So this class should have all the variable content on which our view is dependent.
 * Every time there is any user input/action we will expose modified
 * copy (to maintain the previous state which is not being modified) of this class.
 * We can create this model using Kotlin's data class.
 *
 * @param EFFECT ViewEffect is useful for actions that are fire-and-forget and we do not
 * want to maintain its state. We can create this class using Kotlin's sealed class.
 *
 * @param ACTION Represents all actions/events a user can perform on the view.
 * This is used to pass user input/action to the ViewModel.
 * We can create this event set using Kotlin's sealed class.
 *
 * @property process(viewEvent: EVENT) Process ViewEvents (viewEvent) passed by Activity/Fragment/View
 *                                     and update ViewState and ViewEffect accordingly.
 *
 * @see <a href="https://medium.com/@rohitss/best-architecture-for-android-mvi-livedata-viewmodel-71a3a5ac7ee3">Article which explains this Custom MVI architecture with Architecture Components.</a>
 * @author Rohit Surwase
 * @author https://github.com/RohitSurwase
 * @version 1.0
 * @since 1.0
 */
abstract class MviViewModel<STATE, EFFECT, ACTION>(initialState: STATE) : ViewModel(), ViewModelContract<ACTION> {

    open val isLoggingEnabled: Boolean = false

    private val _viewStates: MutableLiveData<STATE> = MutableLiveData(initialState)
    fun viewStates(): LiveData<STATE> = _viewStates

    private var _viewState: STATE = initialState
    var viewState: STATE
        get() = _viewState
            ?: throw UninitializedPropertyAccessException("\"viewState\" was queried before being initialized")
        set(value) {
            if (isLoggingEnabled)
                Timber.tag(TAG).d("setting viewState : $value")
            _viewState = value
            _viewStates.value = value
        }

    private val _viewEffects: SingleLiveEvent<EFFECT> = SingleLiveEvent()
    fun viewEffects(): SingleLiveEvent<EFFECT> = _viewEffects

    private var _viewEffect: EFFECT? = null
    var viewEffect: EFFECT
        get() = _viewEffect
            ?: throw UninitializedPropertyAccessException("\"viewEffect\" was queried before being initialized")
        set(value) {
            if (isLoggingEnabled)
                Timber.tag(TAG).d("setting viewEffect : $value")
            _viewEffect = value
            _viewEffects.value = value
        }

    @CallSuper
    override fun process(viewAction: ACTION) {
        if (!viewStates().hasObservers()) {
            throw NoObserverAttachedException("No observer attached.")
        }
        if (isLoggingEnabled)
            Timber.tag(TAG).d("processing viewAction: $viewAction")
    }

    override fun onCleared() {
        super.onCleared()
        if (isLoggingEnabled)
            Timber.tag(TAG).d("onCleared")
    }

    init {
        viewState = initialState
    }

}