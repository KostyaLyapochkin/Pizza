package by.data.local

import by.data.local.preferences.PreferencesStorage
import by.data.database.DatabaseStorage
import by.domain.entities.Drink
import by.domain.entities.Pizza
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Cache @Inject constructor(
    private val preferencesStorage: PreferencesStorage,
    private val database: DatabaseStorage
) {

    fun setFixPrice(fixPrice: Float) {
        preferencesStorage.fixPrice = fixPrice
    }

    fun getFixPrice() = preferencesStorage.fixPrice

    fun getCartDrinksList() = database.getDrinkDao().getDrinksList()

    suspend fun saveDrink(drink: Drink) = database.getDrinkDao().saveDrink(drink)

    suspend fun deleteDrink(key: Int) = database.getDrinkDao().deleteDrink(key)

    suspend fun clearAllDrinks() = database.getDrinkDao().clearAll()

    fun getCartPizzasList() = database.getPizzaDao().getPizzasList()

    suspend fun savePizza(pizza: Pizza) = database.getPizzaDao().savePizza(pizza)

    suspend fun removePizza(key: Int) = database.getPizzaDao().removePizza(key)

    suspend fun clearAllPizzas() = database.getPizzaDao().clearAll()

    fun getCartSize() = database.getPizzaDao().getTableSize()
        .combine(database.getDrinkDao().getTableSize()) { pSize, dSize ->
            pSize + dSize
        }

}