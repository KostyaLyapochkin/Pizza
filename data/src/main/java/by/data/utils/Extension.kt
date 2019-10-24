package by.data.utils

import by.domain.entities.CartItem
import by.domain.entities.CartItemEnum
import by.domain.entities.Drink
import by.domain.entities.Pizza

fun List<Pizza>.pizzasToItemCart(): List<CartItem> {
    val cartItemsList = mutableListOf<CartItem>()
    forEach {
        cartItemsList.add(
            CartItem(
                it.key,
                CartItemEnum.PIZZA,
                it.name,
                it.price
            )
        )
    }
    return cartItemsList
}

fun List<Drink>.drinksToItemCart(): List<CartItem> {
    val cartItemsList = mutableListOf<CartItem>()
    forEach {
        cartItemsList.add(
            CartItem(
                it.key,
                CartItemEnum.DRINK,
                it.name,
                it.price
            )
        )
    }
    return cartItemsList
}
