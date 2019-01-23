package com.bereguliak.processor.generator.processor.tokenizer

import opennlp.tools.tokenize.TokenizerME
import opennlp.tools.tokenize.TokenizerModel
import java.io.File

class TrainedTokenizer(modelPath: String) {

    private val tokenizer: TokenizerME

    init {
        val model = TokenizerModel(File(modelPath))
        tokenizer = TokenizerME(model)
    }

    fun tokenize(sentence: String): Array<String> = tokenizer.tokenize(sentence)

}