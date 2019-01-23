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
        startTime()
        val sentenceFactory: SentenceFactory = SentenceTypeFactory()
        val result = data.ner
                .union(data.classifier)
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
        endTime(data.ner.union(data.classifier).size)
        return Question(DEFAULT_SIMPLE_QUESTION_TITLE, result)
    }
    //endregion

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region Utility structures
    companion object {
        private const val TAG = "QuestionGenerator"
    }
    //endregion
}