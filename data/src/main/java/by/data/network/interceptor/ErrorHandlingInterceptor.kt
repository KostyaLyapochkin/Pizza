package by.data.network.interceptor

import android.content.Context
import by.data.R
import by.data.network.exception.ServerConnectionException
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.net.ConnectException
import java.net.HttpURLConnection.*
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorHandlingInterceptor @Inject constructor(private val context: Context) : Interceptor {

    private var gson: Gson = Gson()

    @Throws(IOException::class)
    override fun intercept(
        chain: Interceptor.Chain
    ): Response = try {
        val request = chain.request()
        val response = chain.proceed(request)
        if (!response.isSuccessful) {
            when (response.code()) {
                HTTP_BAD_REQUEST -> {
                    handleClientError(response)
                }
            }
        }
        response
    } catch (e: SocketTimeoutException) {
        throw ServerConnectionException(context.getString(R.string.error_handling_timeout))
    } catch (e: UnknownHostException) {
        throw ServerConnectionException(context.getString(R.string.server_connection_exception))
    } catch (e: ConnectException) {
        throw ServerConnectionException(context.getString(R.string.server_connection_exception))
    } catch (e: SocketException) {
        throw ServerConnectionException(context.getString(R.string.server_connection_exception))
    }

    @Throws(IOException::class)
    private fun handleClientError(response: Response) {
        try {
            val responseBody = response.body()
            responseBody?.let {
//                with(responseBody.string()) {
//                    if (!TextUtils.isEmpty(this)) {
//                        gson.fromJson(this, ServerErrorBody::class.java)?.let { it ->
//                            throw getExceptionFromError(it)
//                        }
//                    }
//                }
            }
        } finally {
            throw IOException()
        }
    }

}