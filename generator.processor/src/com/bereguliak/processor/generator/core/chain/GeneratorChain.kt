package com.bereguliak.generator.core.chain

import com.bereguliak.generator.model.entity.ReaderChunk

interface GeneratorChain {
    fun linkWith(chain: GeneratorChain): GeneratorChain

    fun handleNext(data: ReaderChunk): ReaderChunk

    fun handle(data: ReaderChunk): ReaderChunk
}