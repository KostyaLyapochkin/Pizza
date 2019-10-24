package by.pizza.features.pizzaprofile.di

import androidx.lifecycle.ViewModel
import by.pizza.di.annotation.ViewModelKey
import by.pizza.features.pizzaprofile.PizzaProfileActivity
import by.pizza.features.pizzaprofile.PizzaProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [PizzaProfileModule::class])
internal interface PizzaProfileComponent {

    fun inject(a: PizzaProfileActivity)

}

@Module
internal interface PizzaProfileModule {

    @Binds
    @IntoMap
    @ViewModelKey(PizzaProfileViewModel::class)
    fun bindPizzaProfileViewModel(viewModel: PizzaProfileViewModel): ViewModel

}