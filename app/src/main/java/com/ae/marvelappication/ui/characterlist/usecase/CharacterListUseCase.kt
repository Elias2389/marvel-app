package com.ae.marvelappication.ui.characterlist.usecase

import com.ae.marvelappication.dto.ResultsItem


interface CharacterListUseCase {

    /**
     * Method to invoke get all Characters with paged
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return List of Characters
     */
    suspend fun invoke(offset: Int, limit: Int): List<ResultsItem>
}