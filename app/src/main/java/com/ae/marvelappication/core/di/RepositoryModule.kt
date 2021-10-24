package com.ae.marvelappication.core.di

import com.ae.marvelappication.common.connectionchecker.CheckConnection
import com.ae.marvelappication.data.datasource.characterdetail.CharacterDetailRemoteDataSource
import com.ae.marvelappication.data.datasource.characterlist.CharacterListLocalDataSource
import com.ae.marvelappication.data.datasource.characterlist.CharacterListRemoteDataSource
import com.ae.marvelappication.ui.characterdetail.repository.CharacterDetailRepository
import com.ae.marvelappication.ui.characterdetail.repository.CharacterDetailRepositoryImpl
import com.ae.marvelappication.ui.characterlist.repository.CharacterListRepository
import com.ae.marvelappication.ui.characterlist.repository.CharacterListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCharacterListRepository(
        remote: CharacterListRemoteDataSource,
        local: CharacterListLocalDataSource,
        checkConnect: CheckConnection
    ): CharacterListRepository = CharacterListRepositoryImpl(remote, local, checkConnect)

    @Provides
    fun provideCharacterDetailRepository(
        remote: CharacterDetailRemoteDataSource
    ): CharacterDetailRepository = CharacterDetailRepositoryImpl(remote)
}