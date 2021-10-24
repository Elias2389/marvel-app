package com.ae.marvelappication.ui.characterlist.repository

import com.ae.marvelappication.dto.ResultsItem

interface CharacterListRepository {

    /**
     * Method to get all Characters with paged
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return List of Characters
     */
    suspend fun getAllCharacters(offset: Int, limit: Int): List<ResultsItem>

}