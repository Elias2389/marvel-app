package com.ae.marvelappication.ui.characterdetail.usercase

import com.ae.marvelappication.dto.ResultsItem

interface CharacterDetailUserCase {

    /**
     * Method to call get Character by id
     *
     * @param characterId id of character
     * @return List of Characters
     */
    suspend fun invoke(characterId: Int): List<ResultsItem>
}