package by.data.database.dao

import androidx.room.*
import by.domain.entities.Drink
import kotlinx.coroutines.flow.Flow

@Dao
interface DrinkDao {

    @Query("SELECT COUNT(`key`) FROM drink")
    fun getTableSize(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDrink(drink: Drink)

    @Query("SELECT * FROM Drink")
    fun getDrinksList(): Flow<List<Drink>>

    @Query("DELETE FROM drink WHERE `key`=:key")
    suspend fun deleteDrink(key: Int)

    @Query("DELETE FROM drink")
    suspend fun clearAll()

}