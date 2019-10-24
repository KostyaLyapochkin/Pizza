package by.pizza.di.components

import android.content.Context
import by.pizza.di.modules.AppModule
import by.pizza.di.modules.RestModule
import by.pizza.di.modules.ViewModelModule
import by.pizza.features.main.di.MainComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RestModule::class, ViewModelModule::class])
internal interface AppComponent {

    val mainComponent: MainComponent
    val context: Context

}