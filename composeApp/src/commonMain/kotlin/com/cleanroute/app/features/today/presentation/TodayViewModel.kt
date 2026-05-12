package com.cleanroute.app.features.today.presentation

import com.cleanroute.app.core.util.BaseViewModel
import com.cleanroute.app.features.jobs.domain.model.CleaningJob
import com.cleanroute.app.features.jobs.domain.repository.JobsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class TodayUiState(val jobsToday: List<CleaningJob> = emptyList())
class TodayViewModel(private val repo: JobsRepository): BaseViewModel() {
    private val _state = MutableStateFlow(TodayUiState())
    val state: StateFlow<TodayUiState> = _state.asStateFlow()
    init { scope.launch { repo.watchTodayJobs().collect { _state.value = TodayUiState(it) } } }
}
