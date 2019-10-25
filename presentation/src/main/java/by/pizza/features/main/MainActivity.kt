package by.pizza.features.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import by.pizza.R
import by.pizza.databinding.ActivityMainBinding
import by.pizza.di.Injector
import by.pizza.features.base.BaseActivity
import by.pizza.features.cart.CartActivity

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layout = R.layout.activity_main

    override fun injectComponent() = Injector.plusMainComponent().inject(this)

    override fun injectViewModel() {
        viewModel = getViewModel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_cart, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuAddCart -> startActivity(CartActivity.newInstance(this))
        }
        return super.onOptionsItemSelected(item)
    }

}
