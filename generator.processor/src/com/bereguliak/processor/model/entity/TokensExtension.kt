package com.bereguliak.processor.model.entity

fun Tokens.buildDoubleTokens(): Array<String> {
    val result = mutableListOf<String>()

    val numberOfTokens = 2
    var addedTokens = 0
    var tokenToWrite = ""
    this.tokens.forEach { token ->
        if (numberOfTokens == addedTokens) {
            result.add(tokenToWrite)
            tokenToWrite = ""
            addedTokens = 0
        }

        tokenToWrite += "$token "
        ++addedTokens
    }

    return result.toTypedArray()
}