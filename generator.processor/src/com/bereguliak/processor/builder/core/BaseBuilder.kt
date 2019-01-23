package com.bereguliak.processor.builder.core

import com.bereguliak.processor.generator.core.time.BaseTimeHandler
import com.bereguliak.processor.model.entity.DataChain

abstract class BaseBuilder<out T>(val data: DataChain) : BaseTimeHandler() {
    abstract fun generate(): T
}