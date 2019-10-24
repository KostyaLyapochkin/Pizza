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
	val key: Int,
	@field:SerializedName("id")
	val id: Int,
	@field:SerializedName("price")
	val price: Float,
	@field:SerializedName("name")
	val name: String,
	var isSelected: Boolean = false
): Parcelable