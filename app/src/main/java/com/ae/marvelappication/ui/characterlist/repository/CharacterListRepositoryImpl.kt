package com.ae.marvelappication.ui.characterlist.repository

import com.ae.marvelappication.common.connectionchecker.CheckConnection
import com.ae.marvelappication.data.datasource.characterlist.CharacterListLocalDataSource
import com.ae.marvelappication.data.datasource.characterlist.CharacterListRemoteDataSource
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.mapper.toEntity
import com.ae.marvelappication.mapper.toResultsItem
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Singleton
class CharacterListRepositoryImpl @Inject constructor(
    private val remoteDataSource: CharacterListRemoteDataSource,
    private val localDataSource: CharacterListLocalDataSource,
    private val checkConnect: CheckConnection
) : CharacterListRepository {
    override suspend fun getAllCharacters(offset: Int, limit: Int): List<ResultsItem> {
        return if (checkConnect.connectionIsAvailable()) {
            withContext(Dispatchers.IO) {
                remoteDataSource.getAllCharacterListRemote(
                    offset,
                    limit
                ).data.results?.map { item -> localDataSource.saveCharacterLocal(item.toEntity()) }
            }
            localDataSource.getAllCharacterListLocal(offset, limit).toResultsItem()
        } else {
            localDataSource.getAllCharacterListLocal(offset, limit).toResultsItem()
        }
    }
}
