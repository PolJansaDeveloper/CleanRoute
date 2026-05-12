package com.cleanroute.app.features.jobs.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOneOrNull
import com.cleanroute.app.core.database.CleanRouteDatabase
import com.cleanroute.app.core.util.endOfTodayMillis
import com.cleanroute.app.core.util.nowMillis
import com.cleanroute.app.core.util.startOfTodayMillis
import com.cleanroute.app.features.jobs.domain.model.CleaningJob
import com.cleanroute.app.features.jobs.domain.model.JobChecklistItem
import com.cleanroute.app.features.jobs.domain.model.JobStatus
import com.cleanroute.app.features.jobs.domain.repository.JobsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class JobsRepositoryImpl(private val db: CleanRouteDatabase) : JobsRepository {
    private val q = db.cleanRouteQueries
    override fun watchAllJobs(): Flow<List<CleaningJob>> = q.selectAllJobs().asFlow().mapToList(Dispatchers.Default).map { it.map(::mapJob) }
    override fun watchTodayJobs(): Flow<List<CleaningJob>> = q.selectJobsForDay(startOfTodayMillis(), endOfTodayMillis()).asFlow().mapToList(Dispatchers.Default).map { it.map(::mapJob) }
    override fun watchJob(jobId: String): Flow<CleaningJob?> = combine(
        q.selectJobById(jobId).asFlow().mapToOneOrNull(Dispatchers.Default),
        q.selectChecklistItemsByJobId(jobId).asFlow().mapToList(Dispatchers.Default)
    ) { job, items -> job?.let { mapJob(it).copy(checklistItems = items.map(::mapItem)) } }
    override suspend fun startJob(jobId: String) { q.updateJobStatus(JobStatus.InProgress.name, nowMillis(), nowMillis(), null, null, jobId) }
    override suspend fun toggleChecklistItem(itemId: String, completed: Boolean) { q.updateJobChecklistItemCompletion(if (completed) 1 else 0, if (completed) nowMillis() else null, itemId) }
    override suspend fun completeJob(jobId: String) { q.updateJobStatus(JobStatus.Completed.name, nowMillis(), null, nowMillis(), null, jobId) }

    private fun mapJob(row: com.cleanroute.app.core.database.Jobs): CleaningJob = CleaningJob(row.id,row.title,row.scheduled_start,JobStatus.valueOf(row.status),row.client_id,row.property_id,row.issue_notes, emptyList())
    private fun mapItem(row: com.cleanroute.app.core.database.Job_checklist_items) = JobChecklistItem(row.id,row.job_id,row.title,row.is_required==1L,row.is_completed==1L,row.completed_at,row.sort_order.toInt())
}
