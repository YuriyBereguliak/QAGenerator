package com.bereguliak.processor.generator

import com.bereguliak.configuration.processor.ProcessorConfig
import com.bereguliak.processor.generator.processor.clasifier.NearMaxentClassifier
import com.bereguliak.processor.generator.processor.clasifier.PluralMaxentClassifier
import com.bereguliak.processor.generator.processor.cleaner.CleanerChain
import com.bereguliak.processor.generator.processor.ner.NerDetectorChain
import com.bereguliak.processor.generator.processor.sentence.SentenceDetectorChain
import com.bereguliak.processor.generator.processor.tokenizer.TokensDetectorChain
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.listeners.OnTextGeneratorResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TextGeneration(private val onTextGeneratorResult: OnTextGeneratorResult) : TextGenerationApi {

    //region TextGenerationApi
    override fun runTextGenerator(sourceText: String, config: ProcessorConfig) {
        GlobalScope.launch {
            val cleaner = CleanerChain()
            cleaner
                    .linkWith(SentenceDetectorChain())
                    .linkWith(TokensDetectorChain())
                    .linkWith(NerDetectorChain())
                    .linkWith(NearMaxentClassifier())
                    .linkWith(PluralMaxentClassifier())

            val result = async {
                cleaner.handle(DataChain(sourceText, config = config))
            }.await()

            onTextGeneratorResult.onResult(result)
        }
    }
    //endregion
}