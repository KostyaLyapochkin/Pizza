package by.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Drink(
	@field: PrimaryKey(autoGenerate = true)
	var key: Int = 0,
	@field:SerializedName("itemKey")
	val id: Int,
	@field:SerializedName("price")
	val price: Float,
	@field:SerializedName("name")
	val name: String
)