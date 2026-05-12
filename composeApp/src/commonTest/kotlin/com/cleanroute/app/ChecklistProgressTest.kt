package com.cleanroute.app

import com.cleanroute.app.features.jobs.domain.model.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ChecklistProgressTest {
    private val items = listOf(
        JobChecklistItem("1","j","A",true,true,1,1),
        JobChecklistItem("2","j","B",true,false,null,2),
        JobChecklistItem("3","j","C",false,true,1,3),
    )
    @Test fun counts(){ assertEquals(2, completedChecklistCount(items)); assertEquals(2, requiredChecklistCount(items)); assertTrue(hasIncompleteRequiredItems(items)) }
}
