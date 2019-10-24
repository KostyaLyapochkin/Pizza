package by.pizza.app

import android.app.Application
import by.pizza.di.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.initAppComponent(this)
    }

}