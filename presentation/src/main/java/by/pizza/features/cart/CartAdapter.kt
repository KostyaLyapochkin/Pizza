package by.pizza.features.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.domain.entities.CartItem
import by.pizza.R
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(
    private val onRemoveClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    internal var cartItemList = mutableListOf<CartItem>()
        set(value) {
            field.clear()
            field.addAll(value)
            notifyDataSetChanged()
        }

    override fun getItemCount() = cartItemList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CartViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_cart,
            parent,
            false
        )
    )

    override fun onViewAttachedToWindow(holder: CartViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.imageButtonRemove.setOnClickListener {
            onRemoveClick(cartItemList[holder.adapterPosition])
            cartItemList.remove(cartItemList[holder.adapterPosition])
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        with(holder) {
            textViewItemCartName.text = cartItemList[position].name ?:
                    textViewItemCartName.context.getString(R.string.custom_pizza_profile_screen)
            textViewItemCartPrice.text = textViewItemCartPrice.context.getString(
                R.string.price_main_screen,
                cartItemList[position].price.toString()
            )
        }
    }

    override fun onViewDetachedFromWindow(holder: CartViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.imageButtonRemove.setOnClickListener(null)
    }

    class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewItemCartName: TextView = itemView.textViewItemCartName
        val textViewItemCartPrice: TextView = itemView.textViewItemCartPrice
        val imageButtonRemove: ImageButton = itemView.imageButtonRemove
    }

}