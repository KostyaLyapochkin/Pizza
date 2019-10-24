package by.pizza.di

import android.content.Context
import by.pizza.di.components.AppComponent
import by.pizza.di.components.DaggerAppComponent
import by.pizza.di.modules.AppModule
import by.pizza.features.addcartdialog.di.AddCartComponent
import by.pizza.features.cart.di.CartComponent
import by.pizza.features.drinks.di.DrinksComponent
import by.pizza.features.main.di.MainComponent
import by.pizza.features.pizzaprofile.di.PizzaProfileComponent

internal object Injector {

    private lateinit var appComponent: AppComponent

    private var mainComponent: MainComponent? = null
    private var pizzaProfileComponent: PizzaProfileComponent? = null
    private var cartComponent: CartComponent? = null
    private var drinksComponent: DrinksComponent? = null

    private var addCartComponent: AddCartComponent? = null

    fun initAppComponent(context: Context) {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(context))
            .build()
    }

    fun plusMainComponent() = mainComponent ?: appComponent.mainComponent.also {
        mainComponent = it
    }

    fun clearMainComponent() {
        mainComponent = null
    }

    fun plusPizzaProfileComponent() =
        pizzaProfileComponent ?: mainComponent!!.pizzaProfileComponent.also {
            pizzaProfileComponent = it
        }

    fun clearPizzaProfileComponent() {
        pizzaProfileComponent = null
    }

    fun plusCartComponent() = cartComponent ?: mainComponent!!.cartComponent.also {
        cartComponent = it
    }

    fun clearCartComponent() {
        cartComponent = null
    }

    fun plusDrinksComponent() = drinksComponent ?: cartComponent!!.drinksComponent.also {
        drinksComponent = it
    }

    fun clearDrinksComponent() {
        drinksComponent = null
    }

    fun plusAddCartComponent() = addCartComponent ?: mainComponent!!.addCartComponent.also {
        addCartComponent = it
    }

    fun clearAddCartComponent() {
        addCartComponent = null
    }

}