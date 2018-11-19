package com.bereguliak.generator.train.tokenizer

import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import com.bereguliak.generator.utility.getTokenizerBinModelPath
import com.bereguliak.generator.utility.getTokenizerDetectorTrainPath
import opennlp.tools.tokenize.TokenSampleStream
import opennlp.tools.tokenize.TokenizerFactory
import opennlp.tools.tokenize.TokenizerME.train
import opennlp.tools.tokenize.TokenizerModel
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class TokenizerDetectorTraining : BaseDetectorTraining() {

    private lateinit var tokenizerModel: TokenizerModel

    //region BaseDetectorTraining
    override fun getTrainPath() = getTokenizerDetectorTrainPath()

    override fun getDestinationModelPath() = getTokenizerBinModelPath()

    override fun train(lineStream: PlainTextByLineStream) {
        tokenizerModel = train(TokenSampleStream(lineStream),
                TokenizerFactory(DEFAULT_LANGUAGE, null, false, null),
                TrainingParameters.defaultParams())
    }


    override fun serializeModel(outputStream: FileOutputStream) {
        tokenizerModel.serialize(outputStream)
    }
    //endregion
}