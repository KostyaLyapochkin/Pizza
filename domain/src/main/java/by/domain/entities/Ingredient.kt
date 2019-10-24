package by.domain.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Ingredient(
	@field: PrimaryKey(autoGenerate = true)
	val ket: Int,
	@field:SerializedName("itemKey")
	val id: Int,
	@field:SerializedName("price")
	val price: Float,
	@field:SerializedName("name")
	val name: String,
	var isSelected: Boolean = false
): Parcelable