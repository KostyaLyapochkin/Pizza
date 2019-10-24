package by.pizza.features.main.di

import androidx.lifecycle.ViewModel
import by.pizza.di.annotation.ViewModelKey
import by.pizza.features.addcartdialog.di.AddCartComponent
import by.pizza.features.cart.di.CartComponent
import by.pizza.features.main.MainActivity
import by.pizza.features.main.MainViewModel
import by.pizza.features.pizzaprofile.di.PizzaProfileComponent
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MainModule::class])
internal interface MainComponent {

    val pizzaProfileComponent: PizzaProfileComponent

    val cartComponent: CartComponent

    val addCartComponent: AddCartComponent

    fun inject(a: MainActivity)

}

@Module
interface MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

}