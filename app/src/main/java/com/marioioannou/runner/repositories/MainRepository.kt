package com.marioioannou.runner.repositories

import com.marioioannou.runner.db.Run
import com.marioioannou.runner.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run: Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run: Run) = runDAO.deleteRun(run)

    fun getAllRunsSortedByDate() = runDAO.getAllSortedByDate()
    fun getAllRunsSortedByDistance() = runDAO.getAllSortedByDistance()
    fun getAllRunsSortedBySpeed() = runDAO.getAllSortedBySpeed()
    fun getAllRunsSortedByCalories() = runDAO.getAllSortedByCalories()
    fun getAllRunsSortedByTime() = runDAO.getAllSortedByTime()

    fun getTotalSpeed() = runDAO.getTotalSpeed()
    fun getTotalTime() = runDAO.getTotalTime()
    fun getTotalCalories() = runDAO.getTotalCalories()
    fun getTotalDistance() = runDAO.getTotalDistance()
}