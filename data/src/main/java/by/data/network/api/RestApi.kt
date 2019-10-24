package by.data.network.api

import by.domain.entities.CheckOut
import by.domain.entities.Drink
import by.domain.entities.Ingredient
import by.domain.entities.PizzasRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RestApi {

    @GET("bins/dokm7")
    suspend fun getPizzasList(): PizzasRequest

    @GET("bins/ozt3z")
    suspend fun getIngredientsList(): List<Ingredient>

    @GET("bins/150da7")
    suspend fun getDrinksList(): List<Drink>

    @POST("post")
    suspend fun checkOut(@Body checkOut: CheckOut)

}