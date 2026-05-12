package com.cleanroute.app.features.jobs.domain.model

enum class JobStatus { Scheduled, InProgress, Completed, Issue, Cancelled }
data class JobChecklistItem(val id:String,val jobId:String,val title:String,val isRequired:Boolean,val isCompleted:Boolean,val completedAt:Long?,val sortOrder:Int)
data class CleaningJob(val id:String,val title:String,val scheduledStart:Long,val status:JobStatus,val clientId:String,val propertyId:String,val issueNotes:String?,val checklistItems:List<JobChecklistItem>)

fun completedChecklistCount(items: List<JobChecklistItem>) = items.count { it.isCompleted }
fun requiredChecklistCount(items: List<JobChecklistItem>) = items.count { it.isRequired }
fun requiredCompletedCount(items: List<JobChecklistItem>) = items.count { it.isRequired && it.isCompleted }
fun checklistProgress(items: List<JobChecklistItem>): Float = if (items.isEmpty()) 0f else completedChecklistCount(items).toFloat() / items.size
fun hasIncompleteRequiredItems(items: List<JobChecklistItem>) = items.any { it.isRequired && !it.isCompleted }
fun canShowReport(job: CleaningJob) = job.status == JobStatus.Completed || job.status == JobStatus.Issue
