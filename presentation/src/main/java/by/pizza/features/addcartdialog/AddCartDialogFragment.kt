package by.pizza.features.addcartdialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import by.pizza.R
import by.pizza.databinding.FragmentAddCartBinding
import by.pizza.di.Injector
import by.pizza.features.base.basedialog.BaseBottomDialogFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AddCartDialogFragment : BaseBottomDialogFragment<AddCartViewModel, FragmentAddCartBinding>() {

    companion object {

        internal fun newInstance() = AddCartDialogFragment()

    }

    private lateinit var job: Job

    override val layout = R.layout.fragment_add_cart

    override fun injectComponent() = Injector.plusAddCartComponent().inject(this)

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        requireDialog().setCanceledOnTouchOutside(false)
    }

    override fun onStart() {
        super.onStart()
        job = viewModel.launch {
            delay(ADD_CART_DELAY)
            dialog!!.dismiss()
        }
    }

}
