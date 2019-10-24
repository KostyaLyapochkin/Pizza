package by.domain.interactors

import by.domain.entities.Drink
import by.domain.repository.IRepository
import javax.inject.Inject

class DrinksInteractor @Inject constructor(
    private val repository: IRepository
) {

    suspend fun getDrinksList() = repository.getDrinksList()

    suspend fun saveDrink(drink: Drink) = repository.saveDrink(drink)

}