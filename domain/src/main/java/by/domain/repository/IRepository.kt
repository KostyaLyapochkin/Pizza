package by.domain.repository

import by.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface IRepository {

    suspend fun getPizzasRequest(): PizzasRequest

    suspend fun getIngredientsList(): List<Ingredient>

    suspend fun getDrinksList(): List<Drink>

    fun getCartList(): Flow<List<CartItem>>

    suspend fun savePizza(pizza: Pizza)

    suspend fun removePizza(key: Int)

    suspend fun saveDrink(drink: Drink)

    suspend fun deleteDrink(key: Int)

    fun getCartSize(): Flow<Int>

    suspend fun checkOut()

}