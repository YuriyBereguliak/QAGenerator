package com.bereguliak.processor.builder.question.factory

import com.bereguliak.processor.model.enums.SentenceType

interface SentenceFactory {
    fun define(text: String): SentenceType
}