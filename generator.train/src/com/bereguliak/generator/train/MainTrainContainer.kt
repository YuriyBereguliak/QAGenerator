package com.bereguliak.generator.train

import com.bereguliak.configuration.train.TrainConfig
import com.bereguliak.generator.train.chunker.ChunkerDetectorTrain
import com.bereguliak.generator.train.ner.NerDetectorTraining
import com.bereguliak.generator.train.pos.PosDetectorTraining
import com.bereguliak.generator.train.sentence.SentenceDetectorTraining
import com.bereguliak.generator.train.tokenizer.TokenizerDetectorTraining

class MainTrainContainer(private val config: TrainConfig) : MainTrainContainerApi {

    //region MainTrainContainerApi
    override fun trainSentenceDetectionModel() {
        SentenceDetectorTraining(config).trainModel()
    }

    override fun trainNerDetectionModel() {
        NerDetectorTraining(config).trainModel()
    }

    override fun trainTokenizerModel() {
        TokenizerDetectorTraining(config).trainModel()
    }

    override fun trainChunkerModel() {
        ChunkerDetectorTrain(config).trainModel()
    }

    override fun trainPosModel() {
        PosDetectorTraining(config).trainModel()
    }
    //endregion

    //region Utility structures
    companion object {
        const val DEFAULT_LANGUAGE = "uk"
    }
    //endregion
}