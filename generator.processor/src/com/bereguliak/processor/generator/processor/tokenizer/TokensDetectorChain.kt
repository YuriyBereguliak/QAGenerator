package com.bereguliak.generator.processor.tokenizer

import com.bereguliak.generator.core.chain.BaseGeneratorChain
import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.entity.Tokens

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