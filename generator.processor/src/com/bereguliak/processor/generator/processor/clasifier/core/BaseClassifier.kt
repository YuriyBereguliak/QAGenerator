package com.bereguliak.processor.generator.processor.clasifier.core

import com.bereguliak.maxent.model.MaxentDataModel
import com.bereguliak.maxent.stream.PlainTextEventStream
import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import opennlp.maxent.GIS
import opennlp.maxent.GISModel

abstract class BaseClassifier : BaseGeneratorChain() {

    private lateinit var model: GISModel

    //region BaseClassifier
    protected fun buildModel(data: MutableList<MaxentDataModel>) {
        val stream = PlainTextEventStream(data)
        model = GIS.trainModel(stream, 100, 1, false, true)
    }

    protected fun eval(text: String): String {
        val nerResult = model.eval(PlainTextEventStream.buildContext(text))
        return model.getBestOutcome(nerResult)
    }
    //endregion
}