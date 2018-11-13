package com.bereguliak.generator.processor.tokenizer

import com.bereguliak.generator.core.chain.BaseGeneratorChain
import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.entity.Tokens
import opennlp.tools.tokenize.WhitespaceTokenizer

class TokensDetectorChain : BaseGeneratorChain() {

    //region TokensDetectorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        data.sentences.forEach { sentence ->
            val result = WhitespaceTokenizer.INSTANCE.tokenize(sentence.text)
            data.tokens.add(Tokens(result))
        }
        return handleNext(data)
    }
    //endregion
}