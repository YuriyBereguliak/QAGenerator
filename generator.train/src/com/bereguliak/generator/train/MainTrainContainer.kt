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
        const val FOLDER_MODELS = "models"
        const val FOLDER_TRAIN = "train"

        const val DEFAULT_LANGUAGE = "uk"

        const val FILE_TRAIN_DATA = "UkraineTrainData.txt"
        const val FILE_SENTENCE_MODEL = "sentence-model.bin"
        const val FILE_TOKENIZER_MODEL = "tokenizer-model.bin"

        const val FILE_NER_MODEL = "ner-model.bin"
    }
    //endregion
}