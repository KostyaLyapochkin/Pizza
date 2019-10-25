package by.pizza

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import by.data.database.DatabaseStorage
import by.data.local.Cache
import by.data.local.Repository
import by.data.network.api.RestApiService
import by.domain.entities.CartItem
import by.domain.entities.CartItemEnum
import by.domain.entities.Drink
import by.domain.entities.Pizza
import by.domain.interactors.CartInteractor
import by.domain.repository.IRepository
import by.pizza.features.cart.CartViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.io.FileNotFoundException

@RunWith(AndroidJUnit4::class)
open class CartViewModelTestExample {

    private var repository: IRepository = mock()
    private lateinit var cartViewModel: CartViewModel

    private val cartItemList = listOf(
        CartItem(1, CartItemEnum.DRINK, "Margarita", 15.0f),
        CartItem(13, CartItemEnum.PIZZA, "Beer", 3.0f)
    )

    @Before
    fun setUp() {
        Mockito.`when`(repository.getCartList()).thenReturn(flowOf(cartItemList))
        cartViewModel = CartViewModel(ApplicationProvider.getApplicationContext<Context>(), CartInteractor(repository))
    }

    @Test
    fun testSumCalculation() = runBlocking<Unit> {
        val job = cartViewModel.updateScreen()
        job.join()
        assertEquals(cartViewModel.commonPrice.get()!!, "18.0")
    }

    @Test
    fun testCheckOut() = runBlocking<Unit> {
        Mockito.`when`(repository.checkOut()).thenReturn(Unit)
        assertEquals(Unit, repository.checkOut())
    }

}