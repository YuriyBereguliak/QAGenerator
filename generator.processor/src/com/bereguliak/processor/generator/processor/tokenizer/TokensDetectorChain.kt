package com.bereguliak.processor.generator.processor.tokenizer

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Tokens

class TokensDetectorChain : BaseGeneratorChain() {

    private var tokenizer: TrainedTokenizer? = null

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region TokensDetectorChain
    override fun handle(data: DataChain): DataChain {
        startTime()
        tokenizer = TrainedTokenizer(data.config.tokenizerModelPath)
        data.sentences.forEach { sentence ->
            val result = tokenizer!!.tokenize(sentence.text)
            data.tokens.add(Tokens(result))
        }

        endTime(data.sentences.size)
        return handleNext(data)
    }
    //endregion

    //region Utility structures
    companion object {
        private const val TAG = "TokensDetector"
    }
    //endregion
}