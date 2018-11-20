package com.bereguliak.processor.generator.processor.tokenizer

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.ReaderChunk
import com.bereguliak.processor.model.entity.Tokens

class TokensDetectorChain : BaseGeneratorChain() {

    private val tokenizer = TrainedTokenizer()

    //region TokensDetectorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        data.sentences.forEach { sentence ->
            val result = tokenizer.tokenize(sentence.text)
            data.tokens.add(Tokens(result))
        }
        return handleNext(data)
    }
    //endregion
}