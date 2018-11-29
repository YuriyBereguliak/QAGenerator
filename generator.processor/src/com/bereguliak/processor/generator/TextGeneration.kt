package com.bereguliak.processor.generator

import com.bereguliak.configuration.processor.ProcessorConfig
import com.bereguliak.processor.generator.processor.cleaner.CleanerChain
import com.bereguliak.processor.generator.processor.ner.NerDetectorChain
import com.bereguliak.processor.generator.processor.sentence.SentenceDetectorChain
import com.bereguliak.processor.generator.processor.tokenizer.TokensDetectorChain
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.listeners.OnTextGeneratorResult

class TextGeneration(private val onTextGeneratorResult: OnTextGeneratorResult) : TextGenerationApi {

    //region TextGenerationApi
    override fun runTextGenerator(sourceText: String, config: ProcessorConfig) {
        val cleaner = CleanerChain()
        cleaner
                .linkWith(SentenceDetectorChain())
                .linkWith(TokensDetectorChain())
                .linkWith(NerDetectorChain())

        val result = cleaner.handle(DataChain(sourceText, config = config))
        onTextGeneratorResult.onResult(result)
    }
    //endregion
}