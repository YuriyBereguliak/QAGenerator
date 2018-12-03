package com.bereguliak.processor.generator.core.time

interface TimeController {
    fun startTime()

    fun endTime(numberOfItems: Int)
}