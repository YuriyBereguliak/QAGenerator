package com.bereguliak.processor.builder.core

import com.bereguliak.processor.model.entity.ReaderChunk

abstract class BaseBuilder<out T>(val data: ReaderChunk) {
    abstract fun generate(): T
}