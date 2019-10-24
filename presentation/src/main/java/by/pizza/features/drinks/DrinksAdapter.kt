package by.pizza.features.drinks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.domain.entities.Drink
import by.pizza.R
import kotlinx.android.synthetic.main.item_drink.view.*

class DrinksAdapter(
    private val onDrinkClick: (Drink) -> Unit
) : RecyclerView.Adapter<DrinksAdapter.DrinksViewHolder>() {

    internal var drinksList = mutableListOf<Drink>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = DrinksViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_drink,
            parent,
            false
        )
    )

    override fun getItemCount() = drinksList.size

    override fun onViewAttachedToWindow(holder: DrinksViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.imageViewAddDrink.setOnClickListener {
            onDrinkClick(drinksList[holder.adapterPosition])
        }
    }

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        with(holder) {
            textViewDrinkName.text = drinksList[position].name
            textViewDrinkPrice.text = textViewDrinkPrice.context
                .getString(R.string.price_main_screen, drinksList[position].price.toString())
        }
    }

    override fun onViewDetachedFromWindow(holder: DrinksViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.imageViewAddDrink.setOnClickListener(null)
    }

    class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewDrinkName: TextView = itemView.textViewDrinkName
        val textViewDrinkPrice: TextView = itemView.textViewDrinkPrice
        val imageViewAddDrink: ImageButton = itemView.imageViewAddDrink
    }
}