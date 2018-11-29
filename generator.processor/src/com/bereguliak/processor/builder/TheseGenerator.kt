package com.bereguliak.processor.builder

import com.bereguliak.generator.utility.GlobalConst.Strings.Companion.DEFAULT_THESES_TITLE
import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Theses

class TheseGenerator(data: DataChain) : BaseBuilder<Theses>(data) {
    //region BaseBuilder
    override fun generate(): Theses {
        return Theses(DEFAULT_THESES_TITLE, data.ner.joinToString(separator = "\n"))
    }
    //endregion
}