package com.bereguliak.generator.train.classifier

import com.bereguliak.configuration.train.TrainConfig
import opennlp.maxent.GIS
import opennlp.maxent.io.SuffixSensitiveGISModelWriter
import opennlp.model.AbstractModel
import opennlp.model.FileEventStream
import opennlp.model.OnePassDataIndexer
import java.io.File


class PluralClassifierTrain(private val config: TrainConfig) {

    fun train() {
        val indexer = OnePassDataIndexer(FileEventStream(config.pluralTrainPath))
        val trainedMaxentModel = GIS.trainModel(100, indexer) // 100 iterations

        val outFile = File(config.pluralModelPath)
        val writer = SuffixSensitiveGISModelWriter(trainedMaxentModel as AbstractModel, outFile)
        writer.persist()
    }
}