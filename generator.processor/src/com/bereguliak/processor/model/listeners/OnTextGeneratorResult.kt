package com.bereguliak.processor.model.listeners

import com.bereguliak.processor.model.entity.DataChain

/**
 * This result called from Worker Thread.
 */
interface OnTextGeneratorResult {
    fun onResult(data: DataChain)
}