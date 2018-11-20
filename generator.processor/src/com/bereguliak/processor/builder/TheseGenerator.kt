package com.bereguliak.processor.builder

import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.ReaderChunk
import com.bereguliak.processor.model.entity.Theses

class TheseGenerator(data: ReaderChunk) : BaseBuilder<Theses>(data) {
    //region BaseBuilder
    override fun generate(): Theses {
        return Theses("", mutableListOf())
    }
    //endregion
}