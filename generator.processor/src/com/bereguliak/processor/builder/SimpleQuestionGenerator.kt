package com.bereguliak.processor.builder

import com.bereguliak.generator.utility.GlobalConst.SimpleQuestions.Companion.SIMPLE_DEFINITION
import com.bereguliak.generator.utility.GlobalConst.SimpleQuestions.Companion.SIMPLE_DESCRIBE
import com.bereguliak.generator.utility.GlobalConst.SimpleQuestions.Companion.SIMPLE_WHAT_QUESTION
import com.bereguliak.generator.utility.GlobalConst.Strings.Companion.DEFAULT_SIMPLE_QUESTION_TITLE
import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.Question
import com.bereguliak.processor.model.entity.ReaderChunk


class SimpleQuestionGenerator(data: ReaderChunk) : BaseBuilder<Question>(data) {
    //region BaseBuilder
    override fun generate(): Question {
        val result = data.ner
                .distinct()
                .withIndex()
                .joinToString(separator = "\n") { (index, value) ->
                    val words = numberOfWords(value.trim())
                    when (words) {
                        0 -> ""
                        1 -> "${index + 1}) $SIMPLE_DEFINITION ${value.toLowerCase()}"
                        2 -> "${index + 1}) $SIMPLE_WHAT_QUESTION ${value.toLowerCase()}"
                        else -> "${index + 1}) $SIMPLE_DESCRIBE ${value.toLowerCase()}"
                    }
                }
        return Question(DEFAULT_SIMPLE_QUESTION_TITLE, result)
    }
    //endregion

    //region Utility API
    private fun numberOfWords(trimmed: String): Int {
        return if (trimmed.isEmpty()) {
            0
        } else {
            trimmed.split("\\s+".toRegex())
                    .dropLastWhile {
                        it.isEmpty()
                    }
                    .toTypedArray()
                    .size
        }
    }
    //endregion
}