package by.pizza.features.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import by.pizza.BR
import by.pizza.features.addcartdialog.AddCartDialogFragment
import javax.inject.Inject

abstract class BaseActivity<VM : BaseViewModel, B : ViewDataBinding> : AppCompatActivity() {

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
        binding = DataBindingUtil.setContentView(this, layout)
        binding.setVariable(BR.viewModel, viewModel)

        with(viewModel) {

            dialogEvent.observe(this@BaseActivity, EventObserver {
                it.show(supportFragmentManager, null)
            })

            toastMessageEvent.observe(this@BaseActivity, EventObserver {
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
            })

            toastMessageEvent.observe(this@BaseActivity, EventObserver {
                Toast.makeText(applicationContext, it, Toast.LENGTH_LONG).show()
            })

            startActivityIntentEvent.observe(this@BaseActivity, EventObserver {
                startActivity(it)
            })

            finishEvent.observe(this@BaseActivity, EventObserver {
                finish()
            })

            addCartEvent.observe(this@BaseActivity, EventObserver {
                AddCartDialogFragment.newInstance().show(supportFragmentManager, null)
            })

        }
    }

    protected inline fun <reified T : ViewModel> getViewModel(): T =
        ViewModelProviders.of(this, viewModelFactory)[T::class.java]

    internal open fun showFragmentWithStack(f: Fragment, id: Int, fragmentTag: String) {
        val fragment = supportFragmentManager.findFragmentByTag(fragmentTag)
        if (fragment == null) {
            supportFragmentManager.commit {
                replace(id, f, fragmentTag)
                addToBackStack(fragmentTag)
            }
        } else {
            supportFragmentManager.commit {
                replace(id, fragment, fragmentTag)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }

}