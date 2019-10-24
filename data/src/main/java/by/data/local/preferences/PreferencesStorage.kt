package by.data.local.preferences

import android.content.Context
import by.data.local.preferences.delegate.FixPriceDelegate
import javax.inject.Inject

class PreferencesStorage @Inject constructor(context: Context) : IPreferencesStorage {

    override var fixPrice: Float by FixPriceDelegate(context)

}