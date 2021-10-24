package com.ae.marvelappication.data.datasource.characterlist

import com.ae.marvelappication.data.dao.ResultItemDao
import com.ae.marvelappication.data.entity.ResultsItemEntity
import com.ae.marvelappication.dto.ResultsItem
import com.ae.marvelappication.mapper.toResultsItem

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CharacterListLocalDataSourceImpl @Inject constructor(
    private val characterDao: ResultItemDao
) : CharacterListLocalDataSource {

    override suspend fun getAllCharacterListLocal(
        offset: Int,
        limit: Int
    ): List<ResultsItemEntity> {
        return characterDao.getAllCharacters(offset, limit)
    }

    override suspend fun saveCharacterLocal(character: ResultsItemEntity) {
        characterDao.insertCharacter(character)
    }

}