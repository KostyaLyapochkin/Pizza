package by.pizza.di.modules

import by.data.network.api.RestApi
import by.pizza.BuildConfig
import by.data.network.interceptor.ErrorHandlingInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
internal class RestModule {

    companion object {

        private const val URL = "https://api.myjson.com"

    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        logging: HttpLoggingInterceptor,
        errorHandlingInterceptor: ErrorHandlingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(errorHandlingInterceptor)
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun provideLogging(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setPrettyPrinting().create()

}