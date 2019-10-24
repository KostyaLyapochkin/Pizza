package by.data.local.preferences.delegate

import android.content.Context

private const val APP_PREFERENCES = "APP_PREFERENCES"

abstract class BaseDelegate(protected val context: Context) {

    protected val sharedPreferences =
        context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)


}