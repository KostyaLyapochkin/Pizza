package by.domain.interactors

import by.domain.entities.CheckOut
import by.domain.entities.Pizza
import by.domain.repository.IRepository
import javax.inject.Inject

open class CartInteractor @Inject constructor(
    private val repository: IRepository
) {

    fun getCartList() = repository.getCartList()

    suspend fun savePizza(pizza: Pizza) = repository.savePizza(pizza)

    suspend fun removePizza(key: Int) = repository.removePizza(key)

    suspend fun removeDrink(key: Int) = repository.deleteDrink(key)

    suspend fun checkOut() = repository.checkOut()

}