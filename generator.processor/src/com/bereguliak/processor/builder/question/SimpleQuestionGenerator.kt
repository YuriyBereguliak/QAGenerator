package com.bereguliak.processor.builder.question

import com.bereguliak.generator.utility.GlobalConst.SimpleQuestions.Companion.SIMPLE_DEFINITION
import com.bereguliak.generator.utility.GlobalConst.SimpleQuestions.Companion.SIMPLE_DESCRIBE
import com.bereguliak.generator.utility.GlobalConst.SimpleQuestions.Companion.SIMPLE_WHAT_QUESTION
import com.bereguliak.generator.utility.GlobalConst.Strings.Companion.DEFAULT_SIMPLE_QUESTION_TITLE
import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.builder.question.factory.SentenceFactory
import com.bereguliak.processor.builder.question.factory.SentenceTypeFactory
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.Question
import com.bereguliak.processor.model.enums.SentenceType

class SimpleQuestionGenerator(data: DataChain) : BaseBuilder<Question>(data) {
    //region BaseBuilder
    override fun generate(): Question {
        val sentenceFactory: SentenceFactory = SentenceTypeFactory()
        val result = data.ner
                .distinct()
                .withIndex()
                .joinToString(separator = "\n") { (index, value) ->
                    when (sentenceFactory.define(value)) {
                        SentenceType.UNDEFINED -> ""
                        SentenceType.DEFINITION -> "${index + 1}) $SIMPLE_DEFINITION ${value.toLowerCase()}"
                        SentenceType.WHAT -> "${index + 1}) $SIMPLE_WHAT_QUESTION ${value.toLowerCase()}"
                        SentenceType.DESCRIBE -> "${index + 1}) $SIMPLE_DESCRIBE ${value.toLowerCase()}"
                    }
                }
        return Question(DEFAULT_SIMPLE_QUESTION_TITLE, result)
    }
    //endregion
}