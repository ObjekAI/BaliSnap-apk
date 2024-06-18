package com.bangkit.balisnap.like

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LikeDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoritedestination: LikeDestination)

    @Delete
    suspend fun delete(favoritedestination: LikeDestination)

    @Query("SELECT * FROM LikeDestination")
    fun getLikeDesti(): LiveData<List<LikeDestination>>

    @Query("SELECT * FROM LikeDestination WHERE name = :name")
    fun getFavoriteByName(name: String): LiveData<LikeDestination>
}