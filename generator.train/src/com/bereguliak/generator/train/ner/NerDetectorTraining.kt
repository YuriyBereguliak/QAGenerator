package com.bereguliak.generator.train.ner

import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import com.bereguliak.generator.utility.getNerNameBinModelPath
import com.bereguliak.generator.utility.getTrainPathName
import opennlp.tools.namefind.*
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class NerDetectorTraining : BaseDetectorTraining() {

    private lateinit var nameFinderModel: TokenNameFinderModel

    //region BaseDetectorTraining
    override fun getTrainPath() = getTrainPathName()

    override fun getDestinationModelPath() = getNerNameBinModelPath()

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