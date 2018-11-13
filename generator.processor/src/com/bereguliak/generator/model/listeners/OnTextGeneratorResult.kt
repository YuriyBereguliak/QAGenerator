package com.bereguliak.generator.model.listeners

import com.bereguliak.generator.model.entity.ReaderChunk

interface OnTextGeneratorResult {
    fun onResult(data: ReaderChunk)
}