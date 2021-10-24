package com.ae.marvelappication.ui.characterdetail.usercase

import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.ui.characterdetail.repository.CharacterDetailRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDetailUserCaseImpl @Inject constructor(
    private val repository: CharacterDetailRepository
): CharacterDetailUserCase {

    override suspend fun invoke(characterId: Int): List<ResultsItem> {
        return repository.getCharacterById(characterId)
    }
}