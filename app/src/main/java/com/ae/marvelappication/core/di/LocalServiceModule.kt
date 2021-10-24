package com.ae.marvelappication.core.di

import android.content.Context
import androidx.room.Room
import com.ae.marvelappication.data.dao.AppDatabase
import com.ae.marvelappication.data.dao.ResultItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalServiceModule {

    private const val DB_NAME: String = "character_db"

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    /**
     * Provide ResultItemDao
     */
    @Provides
    fun provideResultItemDao(appDatabase: AppDatabase): ResultItemDao {
        return appDatabase.resultItemDao()
    }

}