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
                "${calculateExecutionTime()} seconds")
                .logWithOffset()
    }
    //endregion

    //region Utility API
    private fun calculateExecutionTime() = ((endTime - startTime) / 1000) % 60
    //endregion
}