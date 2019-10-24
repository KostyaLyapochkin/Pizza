package by.domain.entities

import android.os.Parcelable
import androidx.room.*
import by.domain.entities.converter.ConverterIngredients
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Pizza(
    @field: PrimaryKey(autoGenerate = true)
    var key: Int = 0,
    @field: SerializedName("name")
    var name: String? = null,
    @field: SerializedName("imageUrl")
    var imageUrl: String? = null,
    @TypeConverters(ConverterIngredients::class)
    @field: SerializedName("ingredients")
    var ingredients: MutableList<Int> = mutableListOf(),
    @Ignore
    var ingredientsString: String? = null,
    var price: Float = 0.0f
) : Parcelable {

    operator fun plus(ingredientPrice: Float): Pizza {
        price += ingredientPrice
        return this
    }

    operator fun minus(ingredientPrice: Float): Pizza {
        price -= ingredientPrice
        return this
    }

    operator fun plus(ingredientName: String): Pizza {
        ingredientsString = if (ingredientsString == null) {
            ingredientName
        } else {
            val builder = StringBuilder(ingredientsString!!)
            builder.append(", $ingredientName").toString()
        }
        return this
    }

}