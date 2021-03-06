package com.bereguliak.processor.generator.processor.ner

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Tokens
import com.bereguliak.processor.model.entity.buildDoubleTokens
import opennlp.tools.namefind.NameFinderME
import opennlp.tools.namefind.TokenNameFinderModel
import opennlp.tools.util.Span
import java.io.File

class NerDetectorChain : BaseGeneratorChain() {

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region BaseGeneratorChain
    override fun handle(data: DataChain): DataChain {
        startTime()
        val path = data.config.nerModelPath
        val detectorModel = TokenNameFinderModel(File(path))
        val nameFinder = NameFinderME(detectorModel)

        data.tokens.forEach { token ->
            val names = nameFinder.find(token.tokens)
            writeToResultData(names, token, data)

            val doubleToken = token.buildDoubleTokens()
            val doubleTokensResult = nameFinder.find(doubleToken)
            writeToResultData(doubleTokensResult, token, data)
        }

        var numberOfTokens =0
        data.tokens.forEach {
            numberOfTokens += it.tokens.size
        }
        endTime(numberOfTokens)
        return handleNext(data)
    }
    //endregion

    //region Utility API
    private fun writeToResultData(names: Array<Span>, token: Tokens, data: DataChain) {
        var name = ""
        names.forEach { span ->
            for (i in span.start until span.end) {
                val part = token.tokens[i]
                if (part.isNotEmpty()) {
                    name += "$part "
                }
            }
        }
        if (name.isNotEmpty()) {
            data.ner.add(name)
        }
    }
    //endregion

    //region Utility structures
    companion object {
        private const val TAG = "NerChain"
    }
    //endregion
}