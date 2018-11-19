package com.bereguliak.generator.model.entity

data class ReaderChunk(var sourceText: String,
                       var sentences: MutableList<Sentence> = mutableListOf(),
                       var tokens: MutableList<Tokens> = mutableListOf(),
                       var trainedTokens: MutableList<Tokens> = mutableListOf())