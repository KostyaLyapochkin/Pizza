package by.pizza.features.drinks

import android.view.View
import androidx.databinding.ObservableField
import by.domain.entities.Drink
import by.domain.interactors.DrinksInteractor
import by.pizza.di.Injector
import by.pizza.features.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class DrinksViewModel @Inject constructor(
    private val drinksInteractor: DrinksInteractor
) : BaseViewModel() {

    val adapter = ObservableField(DrinksAdapter {
        saveDrink(it)
    })



    init {
        launch {
            adapter.get()!!.drinksList = drinksInteractor.getDrinksList().toMutableList()
            progressbarVisibility.set(View.GONE)
            UIVisibility.set(View.VISIBLE)
        }
    }

    private fun saveDrink(drink: Drink) = launch {
        drinksInteractor.saveDrink(drink)
        showAddCartDialog()
    }

    override fun clearComponent() = Injector.clearDrinksComponent()

}