package by.pizza.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.domain.entities.Pizza
import by.pizza.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pizza.view.*

class PizzaAdapter(
    private val onClick: (Pizza) -> Unit,
    private val onAddCartClick: (Pizza) -> Unit
) : RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder>() {

    internal var pizzasList = mutableListOf<Pizza>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PizzaViewHolder = PizzaViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_pizza,
            parent,
            false
        )
    )

    override fun getItemCount() = pizzasList.size

    override fun onViewAttachedToWindow(holder: PizzaViewHolder) {
        super.onViewAttachedToWindow(holder)
        with(holder) {
            itemView.setOnClickListener {
                onClick(pizzasList[adapterPosition])
            }

            linearLayoutAddCart.setOnClickListener {
                onAddCartClick(pizzasList[adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: PizzaViewHolder, position: Int) = with(holder) {
        textViewPizzaName.text = pizzasList[position].name
        textViewPrice.text = textViewPrice.context.getString(R.string.price_main_screen, pizzasList[position].price.toString())
        textViewIngredients.text = pizzasList[position].ingredientsString
        Picasso.get().load(pizzasList[position].imageUrl).into(imageViewPizza)
    }

    override fun onViewDetachedFromWindow(holder: PizzaViewHolder) {
        super.onViewDetachedFromWindow(holder)
        with(holder) {
            itemView.setOnClickListener(null)
            linearLayoutAddCart.setOnClickListener(null)
        }
    }

    class PizzaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPizzaName: TextView = itemView.textViewPizzaName
        val textViewIngredients: TextView = itemView.textViewIngredients
        val linearLayoutAddCart: LinearLayout = itemView.linearLayoutAddCart
        val textViewPrice: TextView = itemView.textViewPrice
        val imageViewPizza: ImageView = itemView.imageViewPizza
    }
}