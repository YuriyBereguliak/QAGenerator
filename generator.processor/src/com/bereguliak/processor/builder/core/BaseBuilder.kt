package com.bereguliak.processor.builder.core

import com.bereguliak.processor.model.entity.DataChain

abstract class BaseBuilder<out T>(val data: DataChain) {
    abstract fun generate(): T
}