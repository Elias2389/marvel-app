package com.ae.marvelappication.ui.characterdetail.repository

import com.ae.marvelappication.data.datasource.characterdetail.CharacterDetailRemoteDataSource
import com.ae.marvelappication.dto.ResultsItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CharacterDetailRepositoryImpl @Inject constructor(
    private val remote: CharacterDetailRemoteDataSource
) : CharacterDetailRepository {

    override suspend fun getCharacterById(characterId: Int): List<ResultsItem> {
        val list = mutableListOf<ResultsItem>()
        withContext(Dispatchers.IO) {
            remote.getCharacterById (characterId).data.results?.let {
                list.addAll(it)
            }
        }
        return list
    }

}