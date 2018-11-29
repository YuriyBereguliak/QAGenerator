package com.bereguliak.processor.model.listeners

import com.bereguliak.processor.model.entity.DataChain

interface OnTextGeneratorResult {
    fun onResult(data: DataChain)
}