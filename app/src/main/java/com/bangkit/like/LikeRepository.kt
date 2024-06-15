package com.bangkit.like

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LikeRepository (application: Application) {
//    private val mLikeDao: LikeDao

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

//    init {
//        val db = LikeDatabase.getDatabase(application)
//        mLikeDao = db.FavoriteDao()
//    }
//
//    fun getFavUserByUsername(username: String): LiveData<LikeDestination> = mLikeDao.getFavUserByUsername(username)
//
//    fun getFavUser(): LiveData<List<LikeDestination>> = mLikeDao.getFavUser()
//
//    fun insert(favoriteuser: LikeDestination) {
//        Log.e("TES REPO", "DATA BERHASIL MASUK")
//        executorService.execute { mLikeDao.insert(favoriteuser) }
//    }
//
//    fun delete(favoriteuser: LikeDestination) {
//        Log.e("TES REPO", "DATA BERHASIL DIHAPUS")
//        executorService.execute { mLikeDao.delete(favoriteuser) }
//    }
}