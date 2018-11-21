package com.bereguliak.maxent.stream

import com.bereguliak.maxent.model.MaxentDataModel
import opennlp.model.Event
import opennlp.model.EventStream

class PlainTextEventStream(dataModels: MutableList<MaxentDataModel>) : EventStream {

    private var iterator: Iterator<MaxentDataModel> = dataModels.iterator()

    //region EventStream
    override fun next(): Event = createEvent(iterator.next())

    override fun hasNext() = iterator.hasNext()
    //endregion

    //region Utility API
    private fun createEvent(sample: MaxentDataModel) = Event(sample.classifier, buildContext(sample.text))
    //endregion

    //region Utility structures
    companion object {
        fun buildContext(text: String): Array<String> = text.split(" ").toTypedArray()
    }
    //endregion
}