package com.bereguliak.generator.model

import java.util.*

data class ReaderChunk(var sentences: Array<String> = emptyArray(), var tokens: Array<String> = emptyArray()) {

    //region Any
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReaderChunk

        if (!Arrays.equals(sentences, other.sentences)) return false
        if (!Arrays.equals(tokens, other.tokens)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = Arrays.hashCode(sentences)
        result = 31 * result + Arrays.hashCode(tokens)
        return result
    }
    //endregion
}