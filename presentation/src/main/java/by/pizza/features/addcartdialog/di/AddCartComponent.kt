package by.pizza.features.addcartdialog.di

import androidx.lifecycle.ViewModel
import by.pizza.di.annotation.ViewModelKey
import by.pizza.features.addcartdialog.AddCartDialogFragment
import by.pizza.features.addcartdialog.AddCartViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [AddCartModule::class])
internal interface AddCartComponent {

    fun inject(f: AddCartDialogFragment)

}

@Module
internal interface AddCartModule {

    @Binds
    @ViewModelKey(AddCartViewModel::class)
    @IntoMap
    fun bindAddCartViewModel(viewModel: AddCartViewModel): ViewModel

}