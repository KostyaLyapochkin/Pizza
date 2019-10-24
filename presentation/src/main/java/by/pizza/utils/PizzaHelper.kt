package by.pizza.utils

import by.domain.entities.Ingredient
import by.domain.entities.Pizza
import javax.inject.Inject

class PizzaHelper @Inject constructor() {

    lateinit var ingredientsList: List<Ingredient>

    fun makeProfile(pizza: Pizza): Pizza {
        pizza.ingredients.forEach { id ->
            ingredientsList.forEach { ingredients ->
                if (id == ingredients.id) {
                    pizza + ingredients.price
                    pizza + ingredients.name
                }
            }
        }
        return pizza
    }

}