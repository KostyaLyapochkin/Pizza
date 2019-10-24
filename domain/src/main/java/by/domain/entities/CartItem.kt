package by.domain.entities

class CartItem(
    val itemKey: Int,
    val cartItemEnum: CartItemEnum,
    val name: String?,
    val price: Float
)

enum class CartItemEnum {
    DRINK, PIZZA
}