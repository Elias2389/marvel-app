package com.ae.marvelappication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "thumbnail")
data class ThumbnailEntity(
    @SerializedName("path")
    @ColumnInfo(name = "path")
    val path: String = "",
    @SerializedName("extension")
    @ColumnInfo(name = "extension")
    val extension: String = ""
)