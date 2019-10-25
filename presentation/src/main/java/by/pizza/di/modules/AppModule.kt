package by.pizza.di.modules

import android.content.Context
import androidx.room.Room
import by.data.database.DatabaseStorage
import by.data.database.PIZZA_DATABASE_STORAGE
import by.data.local.Repository
import by.domain.repository.IRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule.InnerAppModule::class])
internal class AppModule(
    private val context: Context
) {

    @Provides
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideDatabase() = Room.databaseBuilder(
        context,
        DatabaseStorage::class.java,
        PIZZA_DATABASE_STORAGE
    ).build()

    @Module
    interface InnerAppModule {

        @Binds
        @Singleton
        fun provideRepository(repository: Repository): IRepository

    }

}