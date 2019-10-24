package by.pizza.features.drinks.di

import androidx.lifecycle.ViewModel
import by.pizza.di.annotation.ViewModelKey
import by.pizza.features.drinks.DrinksActivity
import by.pizza.features.drinks.DrinksViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [DrinksModule::class])
interface DrinksComponent {

    fun inject(a: DrinksActivity)

}

@Module
interface DrinksModule {

    @IntoMap
    @ViewModelKey(DrinksViewModel::class)
    @Binds
    fun bindDrinksViewModel(viewModel: DrinksViewModel): ViewModel

}