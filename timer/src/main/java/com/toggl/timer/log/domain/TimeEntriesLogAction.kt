package com.toggl.timer.log.domain

import com.toggl.models.common.SwipeDirection
import com.toggl.models.domain.TimeEntry
import com.toggl.timer.common.domain.TimerAction

sealed class TimeEntriesLogAction {
    data class TimeEntryTapped(val id: Long) : TimeEntriesLogAction()
    data class ContinueButtonTapped(val id: Long) : TimeEntriesLogAction()
    data class TimeEntrySwiped(val id: Long, val direction: SwipeDirection) : TimeEntriesLogAction()
    data class TimeEntryDeleted(val deletedTimeEntry: TimeEntry) : TimeEntriesLogAction()
    data class TimeEntryStarted(val startedTimeEntry: TimeEntry, val stoppedTimeEntry: TimeEntry?) :
        TimeEntriesLogAction()

    companion object {
        fun fromTimerAction(timerAction: TimerAction): TimeEntriesLogAction? =
            if (timerAction !is TimerAction.TimeEntriesLog) null
            else timerAction.timeEntriesLogAction

        fun toTimerAction(timeEntriesLogAction: TimeEntriesLogAction): TimerAction =
            TimerAction.TimeEntriesLog(
                timeEntriesLogAction
            )
    }
}

fun TimeEntriesLogAction.formatForDebug() =
    when (this) {
        is TimeEntriesLogAction.ContinueButtonTapped -> "Continue time entry button tapped for id $id"
        is TimeEntriesLogAction.TimeEntrySwiped -> "Time entry with id $id swiped to the $direction "
        is TimeEntriesLogAction.TimeEntryStarted -> "Time entry started $startedTimeEntry"
        is TimeEntriesLogAction.TimeEntryDeleted -> "Time entry deleted $deletedTimeEntry"
        is TimeEntriesLogAction.TimeEntryTapped -> "Tapped time entry with id $id"
    }
