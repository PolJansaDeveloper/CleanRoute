package com.cleanroute.app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.cleanroute.app.features.jobs.presentation.JobsViewModel
import com.cleanroute.app.features.today.presentation.TodayViewModel
import org.koin.compose.koinInject

@Composable
fun App() {
    MaterialTheme {
        var tab by remember { mutableStateOf(0) }
        Scaffold(bottomBar = {
            NavigationBar { listOf("Today", "Jobs", "Settings").forEachIndexed { i, t -> NavigationBarItem(selected = i==tab, onClick = { tab=i }, label={ Text(t)}, icon={}) } }
        }) { p ->
            Box(Modifier.padding(p).fillMaxSize()) { when(tab){0->TodayScreen();1->JobsScreen();else->Text("CleanRoute MVP") } }
        }
    }
}

@Composable private fun TodayScreen(vm: TodayViewModel = koinInject()) { val state by vm.state.collectAsState(); LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) { item { Text("Good morning", style = MaterialTheme.typography.headlineSmall) }; items(state.jobsToday){ Card{ Text(it.title,Modifier.padding(16.dp)) } } } }
@Composable private fun JobsScreen(vm: JobsViewModel = koinInject()) { val state by vm.state.collectAsState(); LazyColumn(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) { items(state.jobs){ Card{ Column(Modifier.padding(16.dp)){ Text(it.title); Text(it.status.name) } } } } }
