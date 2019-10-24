package by.pizza.features.main

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import by.domain.entities.Ingredient
import by.domain.entities.Pizza
import by.domain.entities.PizzasRequest
import by.domain.interactors.PizzaInteractors
import by.pizza.di.Injector
import by.pizza.features.base.BaseViewModel
import by.pizza.features.pizzaprofile.PizzaProfileActivity
import by.pizza.utils.PizzaHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val context: Context,
    private val pizzaInteractors: PizzaInteractors,
    private val pizzaHelper: PizzaHelper
) : BaseViewModel() {

    val progressBarVisibility = ObservableInt(View.VISIBLE)
    val cartCount = ObservableField<String>()
    val visibilityCartCount = ObservableField(View.GONE)
    val pizzasAdapter = ObservableField(PizzaAdapter({
        startActivity(
            PizzaProfileActivity.newInstance(
                context,
                pizzasRequest.basePrice,
                ingredients,
                it
            )
        )
    }) {
        savePizza(it)
        showAddCartDialog()
    })

    private lateinit var pizzasRequest: PizzasRequest
    private lateinit var ingredients: List<Ingredient>

    init {
        showPizzasList()
    }

    private fun showPizzasList() = launch {
        val pizzasRequestDeferred = async { pizzaInteractors.getPizzasList() }
        val ingredientsDeferred = async { pizzaInteractors.getIngredientsList() }

        pizzasRequest = pizzasRequestDeferred.await()
        ingredients = ingredientsDeferred.await()

        pizzasAdapter.get()!!.pizzasList = withContext(Dispatchers.Default) {
            pizzasRequest.pizzas.map {
                it.price = pizzasRequest.basePrice
                pizzaHelper.ingredientsList = ingredients
                pizzaHelper.makeProfile(it)
            }
        }.toMutableList()
        progressBarVisibility.set(View.GONE)

        launch {
            pizzaInteractors.getCartSize().collect {
                cartCount.set(it.toString())
                if (it > 0) {
                    visibilityCartCount.set(View.VISIBLE)
                } else {
                    visibilityCartCount.set(View.GONE)
                }
            }
        }
    }

    fun addCustomPizza() {
        startActivity(
            PizzaProfileActivity.newInstance(
                context,
                pizzasRequest.basePrice,
                ingredients
            )
        )
    }

    private fun savePizza(pizza: Pizza) = launch {
        pizzaInteractors.savePizza(pizza)
    }

    override fun clearComponent() = Injector.clearMainComponent()

}