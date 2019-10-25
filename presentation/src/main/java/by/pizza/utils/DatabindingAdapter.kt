package by.pizza.utils

import android.os.Build
import android.text.Html
import android.widget.TextView
import androidx.databinding.BindingAdapter
import by.pizza.R

object DatabindingAdapter {

    @JvmStatic
    @BindingAdapter("app:addCartSum")
    fun addCartSum(textView: TextView, sumCount: String) {
        textView.text = textView.context.getString(R.string.add_to_cart_message)
        textView.append(" ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.append(Html.fromHtml(" <i>($$sumCount)</i>", Html.FROM_HTML_MODE_COMPACT))
        } else {
            textView.append(Html.fromHtml(" <i>($$sumCount)</i>"))
        }
    }

    @JvmStatic
    @BindingAdapter("app:addCheckOutSum")
    fun addCheckOutSum(textView: TextView, sumCount: String) {
        textView.text = textView.context.getString(R.string.checkout_cart)
        textView.append(" ")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.append(Html.fromHtml(" <i>($$sumCount)</i>", Html.FROM_HTML_MODE_COMPACT))
        } else {
            textView.append(Html.fromHtml(" <i>($$sumCount)</i>"))
        }
    }

}