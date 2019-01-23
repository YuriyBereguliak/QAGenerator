package com.bereguliak.generator.train.tokenizer

import com.bereguliak.configuration.train.TrainConfig
import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import opennlp.tools.tokenize.TokenSampleStream
import opennlp.tools.tokenize.TokenizerFactory
import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class TokenizerDetectorTraining(private val config: TrainConfig) : BaseDetectorTraining() {

    private lateinit var tokenizerModel: TokenizerModel

    //region BaseDetectorTraining
    override fun getTrainPath() = config.tokenizerTrainPath

    override fun getDestinationModelPath() = config.tokenizerModelPath

    override fun train(lineStream: PlainTextByLineStream) {
        tokenizerModel = TokenizerME.train(TokenSampleStream(lineStream),
                TokenizerFactory(DEFAULT_LANGUAGE, null, false, null),
                TrainingParameters.defaultParams())
    }


    override fun serializeModel(outputStream: FileOutputStream) {
        tokenizerModel.serialize(outputStream)
    }
    //endregion
}