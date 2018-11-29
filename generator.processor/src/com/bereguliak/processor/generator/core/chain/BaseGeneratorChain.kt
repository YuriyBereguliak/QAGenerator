package com.bereguliak.processor.generator.core.chain

import com.bereguliak.processor.model.entity.DataChain

abstract class BaseGeneratorChain : GeneratorChain {

    private var nextChain: GeneratorChain? = null

    //region GeneratorChain
    override fun linkWith(chain: GeneratorChain): GeneratorChain {
        nextChain = chain
        return chain
    }

    override fun handleNext(data: DataChain): DataChain {
        return if (nextChain == null) {
            data
        } else {
            nextChain!!.handle(data)
        }
    }
    //endregion
}