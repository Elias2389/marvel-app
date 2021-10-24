package com.ae.marvelappication.data.datasource.characterdetail

import com.ae.marvelappication.data.service.CharacterService
import com.ae.marvelappication.dto.MarvelResponse
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterDetailRemoteDataSourceImpl @Inject constructor(
    private val characterService: CharacterService
) : CharacterDetailRemoteDataSource {

    override suspend fun getCharacterById(characterId: Int): MarvelResponse {
        return characterService.getCharacterById(characterId)
    }
}