package by.pizza.features.base.basedialog

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import by.pizza.BR
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.pizza.features.base.BaseViewModel
import javax.inject.Inject

abstract class BaseDialogFragment<VM : BaseViewModel, B : ViewDataBinding> : DialogFragment() {

    protected open lateinit var viewModel: VM
    protected open lateinit var binding: B

    protected lateinit var viewModelFactory: ViewModelProvider.Factory
        @Inject set

    protected abstract val layout: Int

    protected abstract fun injectComponent()

    protected abstract fun injectViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectComponent()
        super.onCreate(savedInstanceState)
        injectViewModel()
        binding = DataBindingUtil.inflate(LayoutInflater.from(activity),
                layout, null, false)
        binding.setVariable(BR.viewModel, viewModel)
    }

    protected inline fun <reified T : ViewModel> getViewModel(): T =
            ViewModelProviders.of(this, viewModelFactory)[T::class.java]

}