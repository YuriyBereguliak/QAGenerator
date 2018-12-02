package com.bereguliak.processor.generator.processor.clasifier

import com.bereguliak.generator.utility.log
import com.bereguliak.processor.generator.processor.clasifier.core.BaseClassifier
import com.bereguliak.processor.model.entity.DataChain
import opennlp.maxent.io.GISModelReader
import opennlp.model.PlainTextFileDataReader
import java.io.FileInputStream
import java.util.zip.GZIPInputStream


class PluralMaxentClassifier : BaseClassifier() {
    //region BaseGeneratorChain
    override fun handle(data: DataChain): DataChain {
        val inputStream = FileInputStream(data.config.pluralModelPath)
        val decodedInputStream = GZIPInputStream(inputStream)
        val modelReader = PlainTextFileDataReader(decodedInputStream)
        val loadedMaxentModel = GISModelReader(modelReader).getModel()

        val context = arrayOf("a=1", "b=0")
        val outcomeProbs = loadedMaxentModel.eval(context)
        val outcome = loadedMaxentModel.getBestOutcome(outcomeProbs)
        outcome.log()
        return handleNext(data)
    }
    //endregion
}