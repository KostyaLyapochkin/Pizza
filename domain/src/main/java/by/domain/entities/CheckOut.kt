package by.domain.entities

import com.google.gson.annotations.SerializedName

data class CheckOut(
    @field: SerializedName("pizzas")
    val pizzas: List<Pizza>,
    @field: SerializedName("drinks")
    val drinks: List<Drink>
)