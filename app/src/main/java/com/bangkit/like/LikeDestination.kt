package com.bangkit.like

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "LikeDestination")
@Parcelize

data class LikeDestination(
    @PrimaryKey(autoGenerate = false)


    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "image")
    var image : String? = null,

    @ColumnInfo(name = "description")
    var description : String? = null,
) : Parcelable