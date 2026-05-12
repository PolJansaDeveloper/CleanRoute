package com.cleanroute.app.features.jobs.presentation

import com.cleanroute.app.core.util.BaseViewModel
import com.cleanroute.app.features.jobs.domain.model.CleaningJob
import com.cleanroute.app.features.jobs.domain.repository.JobsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class JobDetailUiState(val job: CleaningJob? = null)
class JobDetailViewModel(private val repo: JobsRepository): BaseViewModel() {
    private val _state = MutableStateFlow(JobDetailUiState())
    val state: StateFlow<JobDetailUiState> = _state.asStateFlow()
    fun load(id: String) { scope.launch { repo.watchJob(id).collect { _state.value = JobDetailUiState(it) } } }
    fun startJob(id: String) { scope.launch { repo.startJob(id) } }
}
