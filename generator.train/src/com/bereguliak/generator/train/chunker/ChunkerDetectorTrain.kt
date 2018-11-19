package com.bereguliak.generator.train.chunker

import com.bereguliak.generator.train.MainTrainContainer.Companion.DEFAULT_LANGUAGE
import com.bereguliak.generator.train.core.BaseDetectorTraining
import com.bereguliak.generator.utility.getChunkerModelPath
import com.bereguliak.generator.utility.getChunkerTrainPath
import opennlp.tools.chunker.ChunkSampleStream
import opennlp.tools.chunker.ChunkerFactory
import opennlp.tools.chunker.ChunkerME
import opennlp.tools.chunker.ChunkerModel
import opennlp.tools.util.PlainTextByLineStream
import opennlp.tools.util.TrainingParameters
import java.io.FileOutputStream

class ChunkerDetectorTrain : BaseDetectorTraining() {

    private lateinit var chunkerModel: ChunkerModel

    //region BaseDetectorTraining
    override fun getTrainPath() = getChunkerTrainPath()

    override fun getDestinationModelPath() = getChunkerModelPath()

    override fun train(lineStream: PlainTextByLineStream) {
        chunkerModel = ChunkerME.train(DEFAULT_LANGUAGE,
                ChunkSampleStream(lineStream),
                TrainingParameters.defaultParams(),
                ChunkerFactory())
    }


    override fun serializeModel(outputStream: FileOutputStream) {
        chunkerModel.serialize(outputStream)
    }
    //endregion
}