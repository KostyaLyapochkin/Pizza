package by.pizza.features.pizzaprofile

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import by.domain.entities.Ingredient
import by.domain.entities.Pizza
import by.domain.interactors.PizzaInteractors
import by.pizza.R
import by.pizza.di.Injector
import by.pizza.features.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class PizzaProfileViewModel @Inject constructor(
    private val context: Context,
    private val pizzaInteractors: PizzaInteractors
) : BaseViewModel() {

    val toolbarTitle = ObservableField<String>()
    val commonPrice = ObservableField<String>()

    val adapter = ObservableField(IngredientsAdapter {
        if (it.isSelected) {
            pizza!! + it.price
            pizza!!.ingredients.add(it.id)
        } else {
            pizza!! - it.price
            pizza!!.ingredients.remove(it.id)
        }
        commonPrice.set(pizza!!.price.toString())
    })

    internal var pizza: Pizza? = null
    internal var basePrice: Float = 0.0f
    internal val ingredients = mutableListOf<Ingredient>()

    internal fun updateScreen() {
        pizza!!.apply {
            toolbarTitle.set(name ?: context.getString(R.string.custom_pizza_profile_screen))
            ingredients.forEach { topIngredients ->
                this@PizzaProfileViewModel.ingredients.forEach { nestedIngredients ->
                    if (topIngredients == nestedIngredients.id) {
                        nestedIngredients.isSelected = true
                    }
                }
            }
        }

        if (pizza!!.price == 0.0f) {
            pizza!! + basePrice
        }

        commonPrice.set(pizza!!.price.toString())
        adapter.get()!!.ingredientsList = ingredients
        progressbarVisibility.set(View.GONE)
        UIVisibility.set(View.VISIBLE)
    }

    fun savePizza() {
        launch {
            pizzaInteractors.savePizza(pizza!!)
            showAddCartDialog()
        }
    }

    override fun clearComponent() = Injector.clearPizzaProfileComponent()

}