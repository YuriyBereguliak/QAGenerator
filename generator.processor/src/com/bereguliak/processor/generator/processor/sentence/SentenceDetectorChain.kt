package com.bereguliak.generator.processor.sentence

import com.bereguliak.generator.core.chain.BaseGeneratorChain
import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.entity.Sentence
import com.bereguliak.generator.utility.getSentenceBinModelPath
import opennlp.tools.sentdetect.SentenceDetectorME
import opennlp.tools.sentdetect.SentenceModel
import java.io.File

class SentenceDetectorChain : BaseGeneratorChain() {

    //region BaseGeneratorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        val detectorModel = SentenceModel(File(getSentenceBinModelPath()))
        val sentenceDetector = SentenceDetectorME(detectorModel)
        val result = sentenceDetector.sentDetect(data.sourceText)
        result.forEach {
            data.sentences.add(Sentence(it))
        }
        return handleNext(data)
    }
    //endregion
}