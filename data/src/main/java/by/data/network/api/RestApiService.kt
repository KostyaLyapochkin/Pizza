package by.data.network.api

import by.domain.entities.CheckOut
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestApiService @Inject constructor(
    private val restApi: RestApi
) {

    suspend fun getPizzasList() = restApi.getPizzasList()

    suspend fun getIngredientsList() = restApi.getIngredientsList()

    suspend fun getDrinksList() = restApi.getDrinksList()

    suspend fun checkOut(checkOut: CheckOut) = restApi.checkOut(checkOut)

}
