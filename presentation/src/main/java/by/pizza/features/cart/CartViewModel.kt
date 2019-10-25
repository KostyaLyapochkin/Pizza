package by.pizza.features.cart

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import by.domain.entities.*
import by.domain.interactors.CartInteractor
import by.pizza.di.Injector
import by.pizza.features.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

open class CartViewModel @Inject constructor(
    private val context: Context,
    private val cartInteractor: CartInteractor
) : BaseViewModel() {

    val adapter = ObservableField(CartAdapter {
        removeItem(it)
    })

    val commonPrice = ObservableField<String>()
    override val progressbarVisibility = ObservableInt(View.GONE)

    init {
        updateScreen()
    }

    fun updateScreen() = launch {

        cartInteractor.getCartList().collect {
            var cartSum = 0.0f
            it.forEach {
                cartSum += it.price
            }
            commonPrice.set(cartSum.toString())
            adapter.get()!!.cartItemList = it.toMutableList()
        }

    }

    private fun removeItem(cartItem: CartItem) = launch {
        when(cartItem.cartItemEnum) {
            CartItemEnum.PIZZA -> cartInteractor.removePizza(cartItem.itemKey)
            CartItemEnum.DRINK -> cartInteractor.removeDrink(cartItem.itemKey)
        }
    }

    fun checkOut() {
        launch {
            cartInteractor.checkOut()
        }
    }

    override fun clearComponent() = Injector.clearCartComponent()

}
