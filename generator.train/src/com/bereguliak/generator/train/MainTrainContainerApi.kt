package com.bereguliak.generator.train

interface MainTrainContainerApi {
    fun trainSentenceDetectionModel()

    fun trainNerDetectionModel()

    fun trainTokenizerModel()

    fun trainChunkerModel()

    fun trainPosModel()
}