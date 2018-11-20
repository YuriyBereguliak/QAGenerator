package com.bereguliak.processor.generator.processor.ner

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.ReaderChunk
import com.bereguliak.generator.utility.getNerNameBinModelPath
import com.bereguliak.generator.utility.log
import opennlp.tools.namefind.NameFinderME
import opennlp.tools.namefind.TokenNameFinderModel
import java.io.File

class NerDetectorChain : BaseGeneratorChain() {

    //region BaseGeneratorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        val detectorModel = TokenNameFinderModel(File(getNerNameBinModelPath()))
        val nameFinder = NameFinderME(detectorModel)

        data.tokens.forEach { token ->
            val names = nameFinder.find(token.tokens)

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

        return handleNext(data)
    }
    //endregion
}