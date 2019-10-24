package by.pizza.features.base

import androidx.lifecycle.ViewModel

abstract class LifeCycleViewModel : ViewModel() {

    open fun onCreate() = Unit

    open fun onStart() = Unit

    open fun onResume() = Unit

    open fun onPause() = Unit

    open fun onStop() = Unit

    open fun onDestroy() = Unit
    
}