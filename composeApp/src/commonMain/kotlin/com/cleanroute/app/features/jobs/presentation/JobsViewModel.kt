package com.cleanroute.app.features.jobs.presentation

import com.cleanroute.app.core.util.BaseViewModel
import com.cleanroute.app.features.jobs.domain.model.CleaningJob
import com.cleanroute.app.features.jobs.domain.repository.JobsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class JobsUiState(val jobs: List<CleaningJob> = emptyList())
class JobsViewModel(private val repo: JobsRepository): BaseViewModel() {
    private val _state = MutableStateFlow(JobsUiState())
    val state: StateFlow<JobsUiState> = _state.asStateFlow()
    init { scope.launch { repo.watchAllJobs().collect { _state.value = JobsUiState(it) } } }
}
