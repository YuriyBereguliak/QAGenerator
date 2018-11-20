package com.bereguliak.processor.builder

import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.Question
import com.bereguliak.processor.model.entity.ReaderChunk

class QuestionGenerator(data: ReaderChunk) : BaseBuilder<Question>(data) {
    //region BaseBuilder
    override fun generate(): Question {
        return Question("")
    }
    //endregion
}