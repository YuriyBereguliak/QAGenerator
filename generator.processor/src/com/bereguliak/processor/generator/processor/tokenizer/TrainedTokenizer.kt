package com.bereguliak.processor.generator.processor.tokenizer

import com.bereguliak.generator.utility.getTokenizerBinModelPath
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import java.io.File

class TrainedTokenizer {

    private val tokenizer: TokenizerME

    init {
        val model = TokenizerModel(File(getTokenizerBinModelPath()))
        tokenizer = TokenizerME(model)
    }

    fun tokenize(sentence: String): Array<String> = tokenizer.tokenize(sentence)

}