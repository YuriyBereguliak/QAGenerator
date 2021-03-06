package com.bereguliak.generator.train.ner

import com.bereguliak.configuration.train.TrainConfig
import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import opennlp.tools.namefind.*
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class NerDetectorTraining(private val config: TrainConfig) : BaseDetectorTraining() {

    private lateinit var nameFinderModel: TokenNameFinderModel

    //region BaseDetectorTraining
    override fun getTrainPath() = config.nerTrainPath

    override fun getDestinationModelPath() = config.nerModelPath

    override fun train(lineStream: PlainTextByLineStream) {
        val dataStream = NameSampleDataStream(lineStream)
        nameFinderModel = NameFinderME.train(DEFAULT_LANGUAGE, null, dataStream,
                TrainingParameters.defaultParams(),
                TokenNameFinderFactory.create(null, null, emptyMap(), BioCodec()))
    }

    override fun serializeModel(outputStream: FileOutputStream) {
        nameFinderModel.serialize(outputStream)
    }
    //endregion
}