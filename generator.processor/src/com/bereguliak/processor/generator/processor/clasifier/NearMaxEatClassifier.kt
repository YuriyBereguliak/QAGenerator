package com.bereguliak.processor.generator.processor.clasifier

import com.bereguliak.generator.utility.log
import com.bereguliak.maxent.model.MaxentDataModel
import com.bereguliak.maxent.stream.PlainTextEventStream
import com.bereguliak.maxent.stream.PlainTextEventStream.Companion.buildContext
import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.ReaderChunk
import opennlp.maxent.GIS

class NearMaxEatClassifier : BaseGeneratorChain() {

    //region BaseGeneratorChain
    override fun handle(data: ReaderChunk): ReaderChunk {
        val mutableListOf = mutableListOf(
                MaxentDataModel("1", "Комп'ютер"),
                MaxentDataModel("1", "Штучний інтелект"),
                MaxentDataModel("1", "Мережі"),
                MaxentDataModel("1", "комутатор"),
                MaxentDataModel("2", "комутатор мережевий пристрій")
        )
        val stream = PlainTextEventStream(mutableListOf)
        val model = GIS.trainModel(stream, 100, 1, false, true)
        val result = model.eval(buildContext("комутатор"))
        model.getBestOutcome(result).log()
        return handleNext(data)
    }
    //endregion
}