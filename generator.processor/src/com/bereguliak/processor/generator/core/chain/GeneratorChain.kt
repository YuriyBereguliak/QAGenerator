package com.bereguliak.processor.generator.core.chain

import com.bereguliak.processor.model.entity.ReaderChunk

interface GeneratorChain {
    fun linkWith(chain: GeneratorChain): GeneratorChain

    fun handleNext(data: ReaderChunk): ReaderChunk

    fun handle(data: ReaderChunk): ReaderChunk
}