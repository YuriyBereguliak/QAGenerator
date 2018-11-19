package com.bereguliak.generator.train.pos

import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import com.bereguliak.generator.utility.getPosBinModelPath
import com.bereguliak.generator.utility.getPosTrainPath
import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTaggerME
import opennlp.tools.postag.WordTagSampleStream
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class PosDetectorTraining : BaseDetectorTraining() {

    private lateinit var posModel: POSModel

    //region BaseDetectorTraining
    override fun getTrainPath() = getPosTrainPath()

    override fun getDestinationModelPath() = getPosBinModelPath()

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