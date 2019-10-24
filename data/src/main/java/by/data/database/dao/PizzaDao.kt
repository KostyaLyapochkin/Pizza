package by.data.database.dao

import androidx.room.*
import by.domain.entities.Pizza
import kotlinx.coroutines.flow.Flow

@Dao
interface PizzaDao {

    @Query("SELECT COUNT(`key`) FROM pizza")
    fun getTableSize(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePizza(pizza: Pizza)

    @Query("SELECT * FROM Pizza")
    fun getPizzasList(): Flow<List<Pizza>>

    @Query("DELETE FROM pizza WHERE `key`=:key")
    suspend fun removePizza(key: Int)

    @Query("DELETE FROM Pizza")
    suspend fun clearAll()

}