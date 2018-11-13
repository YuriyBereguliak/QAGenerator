package com.bereguliak.generator

import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.listeners.OnTextGeneratorResult
import com.bereguliak.generator.processor.sentence.SentenceDetectorChain
import com.bereguliak.generator.processor.tokenizer.TokensDetectorChain

class TextGeneration(private val onTextGeneratorResult: OnTextGeneratorResult) : TextGenerationApi {

    //region TextGenerationApi
    override fun runTextGenerator(sourceText: String) {
        val sentence = SentenceDetectorChain()
        sentence.linkWith(TokensDetectorChain())

        val result = sentence.handle(ReaderChunk(sourceText))
        onTextGeneratorResult.onResult(result)
    }
    //endregion
}