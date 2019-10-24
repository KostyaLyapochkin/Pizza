package by.pizza.features.addcartdialog

import by.pizza.di.Injector
import by.pizza.features.base.BaseViewModel
import javax.inject.Inject

const val ADD_CART_DELAY = 3000L

class AddCartViewModel @Inject constructor(): BaseViewModel() {

    override fun clearComponent() = Injector.clearAddCartComponent()

}
