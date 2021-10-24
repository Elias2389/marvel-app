package com.ae.marvelappication.data.service

import com.ae.marvelappication.dto.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {

    /**
     * Get all characters from API
     */
    @GET("characters")
    suspend fun getAllCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): MarvelResponse

    /**
     * Get character by id characters from API
     */
    @GET("characters/{characterId}")
    suspend fun getCharacterById(
        @Path("characterId") characterId: Int
    ): MarvelResponse
}