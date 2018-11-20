package com.bereguliak.processor.generator

import com.bereguliak.processor.model.entity.ReaderChunk
import com.bereguliak.processor.model.listeners.OnTextGeneratorResult
import com.bereguliak.processor.generator.processor.cleaner.CleanerChain
import com.bereguliak.processor.generator.processor.ner.NerDetectorChain
import com.bereguliak.processor.generator.processor.sentence.SentenceDetectorChain
import com.bereguliak.processor.generator.processor.tokenizer.TokensDetectorChain

class TextGeneration(private val onTextGeneratorResult: OnTextGeneratorResult) : TextGenerationApi {

    //region TextGenerationApi
    override fun runTextGenerator(sourceText: String) {
        val cleaner = CleanerChain()
        cleaner
                .linkWith(SentenceDetectorChain())
                .linkWith(TokensDetectorChain())
                .linkWith(NerDetectorChain())

        val result = cleaner.handle(ReaderChunk(sourceText))
        onTextGeneratorResult.onResult(result)
    }
    //endregion
}