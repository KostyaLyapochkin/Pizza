package by.pizza.features.drinks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.pizza.R
import by.pizza.databinding.ActivityDrinksBinding
import by.pizza.di.Injector
import by.pizza.features.base.BaseActivity

class DrinksActivity : BaseActivity<DrinksViewModel, ActivityDrinksBinding>() {

    companion object {

        internal fun newInstance(context: Context) = Intent(context, DrinksActivity::class.java)

    }

    override val layout = R.layout.activity_drinks

    override fun injectComponent() = Injector.plusDrinksComponent().inject(this)

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

}
