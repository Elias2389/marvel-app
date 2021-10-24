package com.ae.marvelappication.data.datasource.characterlist

import com.ae.marvelappication.dto.MarvelResponse

interface CharacterListRemoteDataSource {

    /**
     * Method to get all Characters with paged from remote service
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set
     * @return Response from Marvel's API
     */
    suspend fun getAllCharacterListRemote(offset: Int, limit: Int): MarvelResponse
}