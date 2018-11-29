package com.bereguliak.generator.train.sentence

import com.bereguliak.configuration.train.TrainConfig
import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import opennlp.tools.sentdetect.SentenceDetectorFactory
import opennlp.tools.sentdetect.SentenceDetectorME
import opennlp.tools.sentdetect.SentenceModel
import opennlp.tools.sentdetect.SentenceSampleStream
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class SentenceDetectorTraining(private val config: TrainConfig) : BaseDetectorTraining() {

    private lateinit var sentenceModel: SentenceModel

    //region BaseDetectorTraining
    override fun getTrainPath() = config.sentenceTrainPath

    override fun getDestinationModelPath() = config.sentenceModelPath

    override fun train(lineStream: PlainTextByLineStream) {
        sentenceModel = SentenceDetectorME.train(DEFAULT_LANGUAGE,
                SentenceSampleStream(lineStream),
                SentenceDetectorFactory(DEFAULT_LANGUAGE, true, null, null),
                TrainingParameters.defaultParams())
    }

    override fun serializeModel(outputStream: FileOutputStream) {
        sentenceModel.serialize(outputStream)
    }
    //endregion
}