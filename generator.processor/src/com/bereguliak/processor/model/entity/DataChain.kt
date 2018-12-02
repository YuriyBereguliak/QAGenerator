package com.bereguliak.processor.model.entity

import com.bereguliak.configuration.processor.ProcessorConfig

data class DataChain(var sourceText: String,
                     var sentences: MutableList<Sentence> = mutableListOf(),
                     var tokens: MutableList<Tokens> = mutableListOf(),
                     var ner: MutableList<String> = mutableListOf(),
                     var classifier: MutableList<String> = mutableListOf(),
                     val config: ProcessorConfig)