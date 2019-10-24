package by.pizza.features.pizzaprofile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.domain.entities.Ingredient
import by.domain.entities.Pizza
import by.pizza.R
import by.pizza.databinding.ActivityPizzaProfileBinding
import by.pizza.di.Injector
import by.pizza.features.base.BaseActivity
import com.squareup.picasso.Picasso

class PizzaProfileActivity : BaseActivity<PizzaProfileViewModel, ActivityPizzaProfileBinding>() {

    companion object {

        private const val PIZZA_EXTRA = "PIZZA_EXTRA"
        private const val BASE_PRICE = "BASE_PRICE"
        private const val INGREDIENT_ARRAY_EXTRA = ""

        internal fun newInstance(
            context: Context,
            basePrice: Float,
            ingredient: List<Ingredient>,
            pizza: Pizza = Pizza()
        ) = Intent(context, PizzaProfileActivity::class.java)
            .putExtra(PIZZA_EXTRA, pizza)
            .putExtra(BASE_PRICE, basePrice)
            .putParcelableArrayListExtra(INGREDIENT_ARRAY_EXTRA, ArrayList(ingredient))

    }

    override val layout = R.layout.activity_pizza_profile

    override fun injectComponent() = Injector.plusPizzaProfileComponent().inject(this)

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.run {
            if (pizza == null) {
                pizza = intent.getParcelableExtra<Pizza>(PIZZA_EXTRA)!!
                viewModel.basePrice = intent.getFloatExtra(BASE_PRICE, 0.0f)
                viewModel.ingredients.addAll(intent.getParcelableArrayListExtra(INGREDIENT_ARRAY_EXTRA)!!)
                if (pizza!!.imageUrl != null) {
                    Picasso.get().load(pizza!!.imageUrl).into(binding.imageViewPizza)
                } else {
                    Picasso.get().load(R.drawable.ic_custom_pizza).into(binding.imageViewPizza)
                }
                updateScreen()
            }
        }
        initViews()
    }

    private fun initViews() {
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}
