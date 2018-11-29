package com.bereguliak.processor.generator

import com.bereguliak.configuration.processor.ProcessorConfig

interface TextGenerationApi {
    fun runTextGenerator(sourceText: String, config: ProcessorConfig)
}