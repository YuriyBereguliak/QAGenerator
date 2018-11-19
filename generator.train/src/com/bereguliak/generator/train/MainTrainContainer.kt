package com.bereguliak.generator.train

import com.bereguliak.generator.train.chunker.ChunkerDetectorTrain
import com.bereguliak.generator.train.ner.NerDetectorTraining
import com.bereguliak.generator.train.pos.PosDetectorTraining
import com.bereguliak.generator.train.sentence.SentenceDetectorTraining
import com.bereguliak.generator.train.tokenizer.TokenizerDetectorTraining

class MainTrainContainer : MainTrainContainerApi {

    //region MainTrainContainerApi
    override fun trainSentenceDetectionModel() {
        SentenceDetectorTraining().trainModel()
    }

    override fun trainNerDetectionModel() {
        NerDetectorTraining().trainModel()
    }

    override fun trainTokenizerModel() {
        TokenizerDetectorTraining().trainModel()
    }

    override fun trainChunkerModel() {
        ChunkerDetectorTrain().trainModel()
    }

    override fun trainPosModel() {
        PosDetectorTraining().trainModel()
    }
    //endregion

    //region Utility structures
    companion object {
        const val DEFAULT_LANGUAGE = "uk"
    }
    //endregion
}