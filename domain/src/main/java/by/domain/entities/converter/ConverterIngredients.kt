package by.domain.entities.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.ArrayList

object ConverterIngredients {
    private val gson = Gson()

    @TypeConverter
    @JvmStatic
    fun fromString(value: String): MutableList<Int> {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson<ArrayList<Int>>(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: MutableList<Int>): String = gson.toJson(list)

}