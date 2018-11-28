package com.bereguliak.processor.generator.processor.ner

import com.bereguliak.generator.utility.getNerNameBinModelPath
import com.bereguliak.generator.utility.log
import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.ReaderChunk
import com.bereguliak.processor.model.entity.Tokens
import com.bereguliak.processor.model.entity.buildDoubleTokens
import opennlp.tools.namefind.NameFinderME
import opennlp.tools.namefind.TokenNameFinderModel
import opennlp.tools.util.Span
import java.io.File

class NerDetectorChain : BaseGeneratorChain() {

    //region BaseGeneratorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        val detectorModel = TokenNameFinderModel(File(getNerNameBinModelPath()))
        val nameFinder = NameFinderME(detectorModel)

        data.tokens.forEach { token ->
            val names = nameFinder.find(token.tokens)
            writeToResultData(names, token, data)

            val doubleToken = token.buildDoubleTokens()
            val doubleTokensResult = nameFinder.find(doubleToken)
            writeToResultData(doubleTokensResult, token, data)
        }

        return handleNext(data)
    }
    //endregion

    //region Utility API
    private fun writeToResultData(names: Array<Span>, token: Tokens, data: ReaderChunk) {
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

        token.tokens.toList().toString().log()
        name.log()
    }
    //endregion
}