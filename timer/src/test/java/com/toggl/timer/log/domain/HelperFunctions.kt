package com.toggl.timer.log.domain

import com.toggl.architecture.core.SettableValue

fun createEmptyState() =
    TimeEntriesLogState(listOf(), mapOf(), null)

fun TimeEntriesLogState.toSettableValue(setFunction: (TimeEntriesLogState) -> Unit) =
    SettableValue({ this }, setFunction)