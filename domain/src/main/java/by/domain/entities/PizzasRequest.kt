package by.domain.entities

import com.google.gson.annotations.SerializedName

data class PizzasRequest(
    @field: SerializedName("basePrice")
    val basePrice: Float,
    @field: SerializedName("pizzas")
    val pizzas: List<Pizza>
)