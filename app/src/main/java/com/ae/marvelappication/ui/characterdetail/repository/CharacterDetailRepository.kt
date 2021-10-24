package com.ae.marvelappication.ui.characterdetail.repository

import com.ae.marvelappication.dto.ResultsItem

interface CharacterDetailRepository {

    /**
     * Method to get Character by id
     *
     * @param characterId id of character
     * @return List of Characters
     */
    suspend fun getCharacterById(characterId: Int): List<ResultsItem>
}