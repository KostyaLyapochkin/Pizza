package by.pizza.features.cart

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import by.pizza.R
import by.pizza.databinding.ActivityCartBinding
import by.pizza.di.Injector
import by.pizza.features.base.BaseActivity
import by.pizza.features.drinks.DrinksActivity

class CartActivity : BaseActivity<CartViewModel, ActivityCartBinding>() {

    companion object {

        internal fun newInstance(context: Context) = Intent(context, CartActivity::class.java)

    }

    override val layout = R.layout.activity_cart

    override fun injectComponent() = Injector.plusCartComponent().inject(this)

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_drinks, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.drinksMenu -> startActivity(DrinksActivity.newInstance(this))
        }
        return super.onOptionsItemSelected(item)
    }

}
