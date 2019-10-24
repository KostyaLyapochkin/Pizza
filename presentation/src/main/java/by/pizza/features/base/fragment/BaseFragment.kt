package by.pizza.features.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.pizza.features.base.BaseViewModel
import by.pizza.features.base.EventObserver
import by.pizza.BR
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : DialogFragment() {

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

        with(viewModel) {

            dialogEvent.observe(this@BaseFragment, EventObserver {
                showDialog(it)
            })

            toastMessageEvent.observe(this@BaseFragment, EventObserver {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            })

            startActivityIntentEvent.observe(this@BaseFragment, EventObserver {
                startActivity(it)
            })

            finishEvent.observe(this@BaseFragment, EventObserver {
                requireActivity().finish()
            })

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        binding.setVariable(BR.viewModel, viewModel)
        return binding.root
    }

    protected fun closeCurrentFragment() {
        if (requireActivity().supportFragmentManager.backStackEntryCount > 0) {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    protected inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]

    internal open fun showFragmentWithStack(f: Fragment, id: Int, fragmentTag: String) = with(requireActivity()) {
        val fragment = requireActivity().supportFragmentManager.findFragmentByTag(fragmentTag)
        if (fragment == null) {
            requireActivity().supportFragmentManager.commit {
                replace(id, f, fragmentTag)
                addToBackStack(fragmentTag)
            }
        } else {
            supportFragmentManager.commit {
                replace(id, fragment, fragmentTag)
            }
        }
    }

    protected open fun showDialog(dialog: DialogFragment) {
        dialog.show(childFragmentManager, null)
    }

}