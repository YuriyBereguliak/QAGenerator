package com.bereguliak.generator.train.core

import com.bereguliak.generator.utility.logError
import opennlp.tools.util.MarkableFileInputStreamFactory
import opennlp.tools.util.PlainTextByLineStream
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.nio.charset.StandardCharsets

abstract class BaseDetectorTraining : BaseDetectorTrainingApi {

    //region BaseDetectorTraining
    abstract fun getTrainPath(): String

    abstract fun train(lineStream: PlainTextByLineStream)

    abstract fun getDestinationModelPath(): String

    abstract fun serializeModel(outputStream: FileOutputStream)
    //endregion

    //region BaseDetectorTrainingApi
    override fun trainModel() {
        try {
            val trainInputStream = MarkableFileInputStreamFactory(File(getTrainPath()))
            val plainStream = PlainTextByLineStream(trainInputStream, StandardCharsets.UTF_16)
            train(plainStream)
            writeTrainedModelToFile()
        } catch (e: FileNotFoundException) {
            e.logError()
        } catch (e1: IOException) {
            e1.logError()
        }
    }
    //endregion

    //region Utility API
    private fun writeTrainedModelToFile() {
        val output = File(getDestinationModelPath())
        val outputStream = FileOutputStream(output)
        try {
            serializeModel(outputStream)
        } finally {
            outputStream.flush()
            outputStream.close()
        }
    }
    //endregion
}