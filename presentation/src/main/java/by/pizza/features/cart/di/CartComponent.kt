package by.pizza.features.cart.di

import androidx.lifecycle.ViewModel
import by.pizza.di.annotation.ViewModelKey
import by.pizza.features.cart.CartActivity
import by.pizza.features.cart.CartViewModel
import by.pizza.features.drinks.di.DrinksComponent
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [CartModule::class])
internal interface CartComponent {

    val drinksComponent: DrinksComponent

    fun inject(a: CartActivity)

}

@Module
internal interface CartModule {

    @Binds
    @IntoMap
    @ViewModelKey(CartViewModel::class)
    fun bindCartViewModel(viewModel: CartViewModel): ViewModel

}