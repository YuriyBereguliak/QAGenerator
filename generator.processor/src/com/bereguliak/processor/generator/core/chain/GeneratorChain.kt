package com.bereguliak.processor.generator.core.chain

import com.bereguliak.processor.model.entity.DataChain

interface GeneratorChain {
    fun linkWith(chain: GeneratorChain): GeneratorChain

    fun handleNext(data: DataChain): DataChain

    fun handle(data: DataChain): DataChain
}