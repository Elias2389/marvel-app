package com.ae.marvelappication.ui.characterlist.usecase

import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterlist.repository.CharacterListRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterListUseCaseImpl @Inject constructor(
    private val repository: CharacterListRepository
) : CharacterListUseCase {

    override suspend fun invoke(offset: Int, limit: Int): List<ResultsItem> {
        return repository.getAllCharacters(offset, limit)
    }
}