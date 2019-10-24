package by.pizza.features.pizzaprofile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.domain.entities.Ingredient
import by.pizza.R
import kotlinx.android.synthetic.main.item_ingredient.view.*
import org.w3c.dom.Text

class IngredientsAdapter(
    private val onSelectIngredient: (Ingredient) -> Unit
) : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {

    internal var ingredientsList = mutableListOf<Ingredient>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = ingredientsList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): IngredientsViewHolder = IngredientsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_ingredient,
            parent,
            false
        )
    )

    override fun onViewAttachedToWindow(holder: IngredientsViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.checkBox.setOnCheckedChangeListener { _, b ->
            onSelectIngredient(ingredientsList[holder.adapterPosition].also { it.isSelected = b })
        }
    }


    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        with(holder) {
            textViewIngredientName.text = ingredientsList[position].name
            textViewIngredientPrice.text = textViewIngredientPrice.context
                .getString(R.string.price_main_screen, ingredientsList[position].price.toString())
            checkBox.isChecked = ingredientsList[position].isSelected
        }
    }

    override fun onViewDetachedFromWindow(holder: IngredientsViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.checkBox.setOnCheckedChangeListener(null)
    }

    class IngredientsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewIngredientName: TextView = itemView.textViewIngredientName
        val textViewIngredientPrice: TextView = itemView.textViewIngredientPrice
        val checkBox: CheckBox = itemView.checkbox
    }
}