package by.domain.interactors

import by.domain.entities.Pizza
import by.domain.repository.IRepository
import javax.inject.Inject

class PizzaInteractors @Inject constructor(
    private val repository: IRepository
) {

    suspend fun getPizzasList() = repository.getPizzasRequest()

    suspend fun getIngredientsList() = repository.getIngredientsList()

    suspend fun savePizza(pizza: Pizza) = repository.savePizza(pizza)

    fun getCartSize() = repository.getCartSize()

}