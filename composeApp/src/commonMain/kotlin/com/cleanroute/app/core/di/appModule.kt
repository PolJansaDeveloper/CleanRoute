package com.cleanroute.app.core.di

import com.cleanroute.app.core.database.CleanRouteDatabaseProvider
import com.cleanroute.app.core.database.DatabaseDriverFactory
import com.cleanroute.app.core.util.generateId
import com.cleanroute.app.core.util.nowMillis
import com.cleanroute.app.features.jobs.data.JobsRepositoryImpl
import com.cleanroute.app.features.jobs.domain.repository.JobsRepository
import com.cleanroute.app.features.jobs.presentation.JobDetailViewModel
import com.cleanroute.app.features.jobs.presentation.JobsViewModel
import com.cleanroute.app.features.today.presentation.TodayViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin(factory: DatabaseDriverFactory) = startKoin {
    modules(module {
        single { CleanRouteDatabaseProvider(factory).database }
        single<JobsRepository> { JobsRepositoryImpl(get()) }
        single { JobsViewModel(get()) }
        single { TodayViewModel(get()) }
        single { JobDetailViewModel(get()) }
        single {
            val q = get<com.cleanroute.app.core.database.CleanRouteDatabase>().cleanRouteQueries
            if (q.selectSetting("seed_completed").executeAsOneOrNull() == null) {
                val now = nowMillis()
                q.insertJob(generateId("job_"), "c1", "p1", null, "Harbour Stay Unit 12 Turnover", now + 3600000, null, null, null, "Scheduled", "Alex", null, null, null, now, now)
                q.insertJob(generateId("job_"), "c2", "p2", null, "Northside Dental Evening Clean", now + 7200000, null, null, null, "InProgress", "Alex", null, null, null, now, now)
                q.upsertSetting("seed_completed", "true")
            }
        }
    })
}
