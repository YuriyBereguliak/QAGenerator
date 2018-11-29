package com.bereguliak.generator.train.pos

import com.bereguliak.configuration.train.TrainConfig
import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTaggerME
import opennlp.tools.postag.WordTagSampleStream
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class PosDetectorTraining(private val config: TrainConfig) : BaseDetectorTraining() {

    private lateinit var posModel: POSModel

    //region BaseDetectorTraining
    override fun getTrainPath() = config.posTrainPath

    override fun getDestinationModelPath() = config.posModelPath

    override fun train(lineStream: PlainTextByLineStream) {
        posModel = POSTaggerME.train(DEFAULT_LANGUAGE,
                WordTagSampleStream(lineStream),
                TrainingParameters.defaultParams(),
                null)
    }

    override fun serializeModel(outputStream: FileOutputStream) {
        posModel.serialize(outputStream)
    }
    //endregion
}