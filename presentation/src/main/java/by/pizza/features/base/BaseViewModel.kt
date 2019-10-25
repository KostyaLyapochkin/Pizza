package by.pizza.features.base

import android.content.Intent
import android.view.View
import androidx.annotation.StringRes
import androidx.databinding.ObservableInt
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.util.*

abstract class BaseViewModel : LifeCycleViewModel(), CoroutineScope {

    private val job = SupervisorJob()
    private val handler = CoroutineExceptionHandler { _, throwable ->
        throwable.message?.let {
            showToast(it)
        }
    }
    private val coroutineName = CoroutineName("viewModelScope=${this::class.java}")
    override val coroutineContext = Dispatchers.Main + job + handler + coroutineName

    open val progressbarVisibility = ObservableInt(View.VISIBLE)
    open val UIVisibility = ObservableInt(View.INVISIBLE)

    internal val startActivityIntentEvent = MutableLiveData<Event<Intent>>()
    internal val toastMessageEvent = MutableLiveData<Event<String>>()
    internal val finishEvent = MutableLiveData<Event<Unit>>()

    internal val dialogEvent = MutableLiveData<Event<DialogFragment>>()

    internal val addCartEvent = MutableLiveData<Event<Unit>>()

    protected fun startActivity(intent: Intent) {
        startActivityIntentEvent.value = Event(intent)
    }

    protected open fun showToast(message: String) {
        toastMessageEvent.value = Event(message)
    }

    protected fun showDialog(dialog: DialogFragment) {
        dialogEvent.value = Event(dialog)
    }

    protected fun closeCurrentActivity() {
        finishEvent.value = Event(Unit)
    }

    protected fun showAddCartDialog() {
        addCartEvent.value = Event(Unit)
    }

    abstract fun clearComponent()

    override fun onCleared() {
        clearComponent()
        job.cancel()
        super.onCleared()
    }

}