package com.ae.marvelappication.data.datasource.characterdetail

import com.ae.marvelappication.dto.MarvelResponse

interface CharacterDetailRemoteDataSource {

    /**
     * Method to get Character by id
     *
     * @param characterId id of character
     * @return List of Characters
     */
    suspend fun getCharacterById(characterId: Int): MarvelResponse
}