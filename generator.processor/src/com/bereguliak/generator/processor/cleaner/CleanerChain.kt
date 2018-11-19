package com.bereguliak.generator.processor.cleaner

import com.bereguliak.generator.core.chain.BaseGeneratorChain
import com.bereguliak.generator.model.entity.ReaderChunk

class CleanerChain : BaseGeneratorChain() {
    //region BaseGeneratorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        val result = data.sourceText
                .replace(",", " ")
                .replace("-", " ")
                .replace("...", " ")
        data.sourceText = result
        return handleNext(data)
    }
    //endregion
}