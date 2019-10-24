package by.pizza.features.base.basedialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.pizza.features.base.BaseViewModel
import by.pizza.BR
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject

abstract class BaseBottomDialogFragment<VM : BaseViewModel, B : ViewDataBinding> : BottomSheetDialogFragment() {

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
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(activity), layout, null, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    protected inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]

}