package by.data.local.preferences.delegate

import android.content.Context
import androidx.core.content.edit
import kotlin.reflect.KProperty

private const val FIX_PRICE = "FIX_PRICE"

class FixPriceDelegate(context: Context) : BaseDelegate(context) {

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Float) {
        sharedPreferences.edit {
            putFloat(FIX_PRICE, value)
        }
    }

    operator fun getValue(thisRef: Any?, property: KProperty<*>) = sharedPreferences.getFloat(FIX_PRICE, 0.0f)

}