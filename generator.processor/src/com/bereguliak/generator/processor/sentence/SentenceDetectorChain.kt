package com.bereguliak.generator.processor.sentence

import com.bereguliak.generator.core.chain.BaseGeneratorChain
import com.bereguliak.generator.model.ReaderChunk

class SentenceDetectorChain : BaseGeneratorChain() {

    //region BaseGeneratorChain
    override fun handle(data: ReaderChunk): ReaderChunk {

        return data
    }
    //endregion
}