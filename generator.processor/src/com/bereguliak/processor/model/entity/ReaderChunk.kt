package com.bereguliak.processor.model.entity

data class ReaderChunk(var sourceText: String,
                       var sentences: MutableList<Sentence> = mutableListOf(),
                       var tokens: MutableList<Tokens> = mutableListOf(),
                       var ner: MutableList<String> = mutableListOf())