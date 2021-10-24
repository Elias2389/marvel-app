package com.ae.marvelappication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ae.marvelappication.data.entity.ResultsItemEntity
import com.ae.marvelappication.dto.ResultsItem

@Dao
interface ResultItemDao {
    /**
     * Method to get data from DB
     *
     * @return results
     */
    @Query("SELECT * FROM character LIMIT :limit OFFSET :offset")
    suspend fun getAllCharacters(offset: Int, limit: Int): List<ResultsItemEntity>

    /**
     * Insert results in DB
     *
     * @param character to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacter(character: ResultsItemEntity)
}