package com.bereguliak.processor.generator.processor.tokenizer

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Tokens

class TokensDetectorChain : BaseGeneratorChain() {

    private var tokenizer: TrainedTokenizer? = null

    //region TokensDetectorChain
    override fun handle(data: DataChain): DataChain {
        tokenizer = TrainedTokenizer(data.config.tokenizerModelPath)
        data.sentences.forEach { sentence ->
            val result = tokenizer!!.tokenize(sentence.text)
            data.tokens.add(Tokens(result))
        }
        return handleNext(data)
    }
    //endregion
}