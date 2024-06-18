package com.bangkit.balisnap.like

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "LikeDestination")
@Parcelize
data class LikeDestination (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "image")
    var image: String? = null,

    @ColumnInfo(name = "description")
    var description: String? = null
) : Parcelable