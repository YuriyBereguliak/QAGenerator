package com.bereguliak.processor.builder.these

import com.bereguliak.generator.utility.GlobalConst.Strings.Companion.DEFAULT_THESES_TITLE
import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Theses

class TheseGenerator(data: DataChain) : BaseBuilder<Theses>(data) {
    //region BaseBuilder
    override fun generate(): Theses {
        startTime()

        val theses = Theses(DEFAULT_THESES_TITLE, data.ner.distinct().joinToString(separator = "\n"))

        endTime(data.ner.size)
        return theses
    }
    //endregion

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region Utility structures
    companion object {
        private const val TAG = "Theses"
    }
    //endregion
}