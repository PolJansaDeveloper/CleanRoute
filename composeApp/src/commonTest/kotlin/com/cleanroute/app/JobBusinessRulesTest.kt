package com.cleanroute.app

import com.cleanroute.app.features.jobs.domain.model.*
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class JobBusinessRulesTest {
    @Test fun reportRules(){ assertTrue(canShowReport(CleaningJob("1","j",1,JobStatus.Completed,"c","p",null, emptyList()))); assertFalse(canShowReport(CleaningJob("1","j",1,JobStatus.Scheduled,"c","p",null, emptyList()))) }
}
