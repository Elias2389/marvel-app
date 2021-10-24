package com.ae.marvelappication.core.di

import com.ae.marvelappication.data.dao.ResultItemDao
import com.ae.marvelappication.data.datasource.characterdetail.CharacterDetailRemoteDataSource
import com.ae.marvelappication.data.datasource.characterdetail.CharacterDetailRemoteDataSourceImpl
import com.ae.marvelappication.data.datasource.characterlist.CharacterListLocalDataSource
import com.ae.marvelappication.data.datasource.characterlist.CharacterListLocalDataSourceImpl
import com.ae.marvelappication.data.datasource.characterlist.CharacterListRemoteDataSource
import com.ae.marvelappication.data.datasource.characterlist.CharacterListRemoteDataSourceImpl
import com.ae.marvelappication.data.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideCharacterListRemoteDataSource(
        characterService: CharacterService
    ): CharacterListRemoteDataSource = CharacterListRemoteDataSourceImpl(characterService)

    @Provides
    fun provideCharacterDetailRemoteDataSource(
        characterService: CharacterService
    ): CharacterDetailRemoteDataSource = CharacterDetailRemoteDataSourceImpl(characterService)

    @Provides
    fun provideCharacterListLocalDataSource(
        characterDao: ResultItemDao
    ): CharacterListLocalDataSource = CharacterListLocalDataSourceImpl(characterDao)
}