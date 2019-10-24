package by.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import by.data.database.dao.DrinkDao
import by.data.database.dao.IngredientDao
import by.data.database.dao.PizzaDao
import by.domain.entities.Drink
import by.domain.entities.Ingredient
import by.domain.entities.Pizza
import by.domain.entities.converter.ConverterIngredients

const val PIZZA_DATABASE_STORAGE = "PIZZA_DATABASE_STORAGE"

@Database(entities = [Ingredient::class, Drink::class, Pizza::class],
    version = 1, exportSchema = false)
@TypeConverters(ConverterIngredients::class)
abstract class DatabaseStorage : RoomDatabase() {

    abstract fun getIngredientDao(): IngredientDao

    abstract fun getDrinkDao(): DrinkDao

    abstract fun getPizzaDao(): PizzaDao

}