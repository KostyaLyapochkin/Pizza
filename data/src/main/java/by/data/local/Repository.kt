package by.data.local

import by.data.network.api.RestApiService
import by.data.utils.drinksToItemCart
import by.data.utils.pizzasToItemCart
import by.domain.entities.*
import by.domain.repository.IRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

open class Repository @Inject constructor(
    private val cache: Cache,
    private val restApiService: RestApiService
) : IRepository {

    override suspend fun getPizzasRequest() = restApiService.getPizzasList()

    override suspend fun getIngredientsList() = restApiService.getIngredientsList()

    override suspend fun getDrinksList() = restApiService.getDrinksList()

    override fun getCartList() = cache.getCartDrinksList()
        .combine(cache.getCartPizzasList()) { dList, pList ->
            dList.drinksToItemCart() + pList.pizzasToItemCart()
        }

    override suspend fun savePizza(pizza: Pizza) = cache.savePizza(pizza)

    override suspend fun removePizza(key: Int) = cache.removePizza(key)

    override suspend fun saveDrink(drink: Drink) = cache.saveDrink(drink)

    override suspend fun deleteDrink(key: Int) = cache.deleteDrink(key)

    override fun getCartSize() = cache.getCartSize()

    override suspend fun checkOut() {
        restApiService.checkOut(
            CheckOut(
                cache.getCartPizzasList().first(),
                cache.getCartDrinksList().first()
            )
        )
        cache.clearAllDrinks()
        cache.clearAllPizzas()
    }

}