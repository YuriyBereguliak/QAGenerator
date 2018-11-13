package com.bereguliak.generator.core.chain

import com.bereguliak.generator.model.entity.ReaderChunk

abstract class BaseGeneratorChain : GeneratorChain {

    private var nextChain: GeneratorChain? = null

    //region GeneratorChain
    override fun linkWith(chain: GeneratorChain): GeneratorChain {
        nextChain = chain
        return chain
    }

    override fun handleNext(data: ReaderChunk): ReaderChunk {
        return if (nextChain == null) {
            data
        } else {
            nextChain!!.handle(data)
        }
    }
    //endregion
}