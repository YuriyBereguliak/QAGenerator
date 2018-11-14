package com.bereguliak.generator.train

import com.bereguliak.generator.train.ner.NerDetectorTraining
import com.bereguliak.generator.train.sentence.SentenceDetectorTraining

class MainTrainContainer : MainTrainContainerApi {

    //region MainTrainContainerApi
    override fun trainSentenceDetectionModel() {
        SentenceDetectorTraining().trainModel()
    }

    override fun trainNerDetectionModel() {
        NerDetectorTraining().trainModel()
    }
    //endregion

    //region Utility structures
    companion object {
        const val DEFAULT_LANGUAGE = "uk"
    }
    //endregion
}