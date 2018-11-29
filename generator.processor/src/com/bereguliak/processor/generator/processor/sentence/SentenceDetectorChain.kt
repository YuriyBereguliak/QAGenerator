package com.bereguliak.processor.generator.processor.sentence

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Sentence
import opennlp.tools.sentdetect.SentenceDetectorME
import opennlp.tools.sentdetect.SentenceModel
import java.io.File

class SentenceDetectorChain : BaseGeneratorChain() {

    //region BaseGeneratorChain
    override fun handle(data: DataChain): DataChain {
        val modelPath = data.config.sentenceModelPath
        val detectorModel = SentenceModel(File(modelPath))
        val sentenceDetector = SentenceDetectorME(detectorModel)
        val result = sentenceDetector.sentDetect(data.sourceText)
        result.forEach {
            data.sentences.add(Sentence(it))
        }
        return handleNext(data)
    }
    //endregion
}