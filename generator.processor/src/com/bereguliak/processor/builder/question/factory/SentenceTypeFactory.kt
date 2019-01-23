package com.bereguliak.processor.builder.question.factory

import com.bereguliak.processor.model.enums.SentenceType

class SentenceTypeFactory : SentenceFactory {
    //region SentenceFactory
    override fun define(text: String): SentenceType {
        return when (numberOfWords(text)) {
            0 -> SentenceType.UNDEFINED
            1 -> SentenceType.DEFINITION
            2 -> SentenceType.WHAT
            else -> SentenceType.DESCRIBE
        }
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