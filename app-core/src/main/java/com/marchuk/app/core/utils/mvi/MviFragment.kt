package com.marchuk.app.core.utils.mvi

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.marchuk.app.core.utils.TAG
import timber.log.Timber

class UnitializedErrorLayoutId : RuntimeException()

/**
 * Create Fragments by Extending this class.
 * Also @see [MviViewModel] for [STATE], [EFFECT] and [ACTION] explanation.
 * @param ViewModel Respective ViewModel class for this fragment which extends [MviViewModel]
 */
abstract class MviFragment<Binding : ViewBinding, STATE, EFFECT, ACTION, ViewModel : MviViewModel<STATE, EFFECT, ACTION>>
    (@LayoutRes layoutId: Int) : Fragment(layoutId) {

    abstract var viewModel: ViewModel
    var _binding: Binding? = null
    val binding: Binding
        get() = _binding!!
    open val isLoggingEnabled = false

    private val viewStateObserver = Observer<STATE> {
        if (isLoggingEnabled)
            Timber.tag(TAG).d("observed viewState : $it")
        renderViewState(it)
    }

    private val viewEffectObserver = Observer<EFFECT> {
        if (isLoggingEnabled)
            Timber.tag(TAG).d("observed viewEffect : $it")
        renderViewEffect(it)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi(savedInstanceState)
        viewModel.viewStates().observe(viewLifecycleOwner, viewStateObserver)
        viewModel.viewEffects().observe(viewLifecycleOwner, viewEffectObserver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun renderViewState(viewState: STATE)

    abstract fun renderViewEffect(viewEffect: EFFECT)

    abstract fun setupUi(savedInstanceState: Bundle?)

    fun processAction(viewAction: ACTION) {
        viewModel.process(viewAction)
    }

}

