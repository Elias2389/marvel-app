package com.ae.marvelappication.core.di

import com.ae.marvelappication.common.connectionchecker.CheckConnection
import com.ae.marvelappication.common.connectionchecker.CheckConnectionImpl
import com.ae.marvelappication.data.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Provides
    fun provideCheckConnection(): CheckConnection = CheckConnectionImpl()

}