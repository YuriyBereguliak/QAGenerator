package com.bereguliak.processor.model.listeners

import com.bereguliak.processor.model.entity.ReaderChunk

interface OnTextGeneratorResult {
    fun onResult(data: ReaderChunk)
}