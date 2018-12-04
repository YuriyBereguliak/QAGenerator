package com.bereguliak.processor.generator.core.time

import com.bereguliak.generator.utility.logWithOffset

abstract class BaseTimeHandler : TimeHandler, TimeController {

    private var startTime: Long = 0L
    private var endTime: Long = 0L

    //region BaseTimeHandler
    override fun startTime() {
        startTime = System.currentTimeMillis()

    }

    override fun endTime(numberOfItems: Int) {
        endTime = System.currentTimeMillis()

        ("Statistics :: ${methodName()} :: number of items :: $numberOfItems :: time :: " +
                "${calculateExecutionTime()} " +
                "")
                .logWithOffset()
    }
    //endregion

    //region Utility API
    private fun calculateExecutionTime(): String {
        val milliseconds = endTime - startTime
        val seconds = (milliseconds / 1000) % 60
        return when (seconds) {
            0L -> "$milliseconds milliseconds"
            else -> "$seconds seconds"
        }
    }
    //endregion
}