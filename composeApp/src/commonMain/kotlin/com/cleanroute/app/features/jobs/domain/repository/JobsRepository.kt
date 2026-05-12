package com.cleanroute.app.features.jobs.domain.repository

import com.cleanroute.app.features.jobs.domain.model.CleaningJob
import kotlinx.coroutines.flow.Flow

interface JobsRepository {
    fun watchAllJobs(): Flow<List<CleaningJob>>
    fun watchTodayJobs(): Flow<List<CleaningJob>>
    fun watchJob(jobId: String): Flow<CleaningJob?>
    suspend fun startJob(jobId: String)
    suspend fun toggleChecklistItem(itemId: String, completed: Boolean)
    suspend fun completeJob(jobId: String)
}
