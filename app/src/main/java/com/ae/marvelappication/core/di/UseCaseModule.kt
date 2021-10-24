package com.ae.marvelappication.core.di

import com.ae.marvelappication.ui.characterdetail.repository.CharacterDetailRepository
import com.ae.marvelappication.ui.characterdetail.usercase.CharacterDetailUserCase
import com.ae.marvelappication.ui.characterdetail.usercase.CharacterDetailUserCaseImpl
import com.ae.marvelappication.ui.characterlist.repository.CharacterListRepository
import com.ae.marvelappication.ui.characterlist.usecase.CharacterListUseCase
import com.ae.marvelappication.ui.characterlist.usecase.CharacterListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideCharacterListUseCase(
        repository: CharacterListRepository
    ): CharacterListUseCase = CharacterListUseCaseImpl(repository)


    @Provides
    fun provideCharacterDetailUserCase(
        repository: CharacterDetailRepository
    ): CharacterDetailUserCase = CharacterDetailUserCaseImpl(repository)

}