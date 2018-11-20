package com.bereguliak.processor.model.entity

data class Tokens(var tokens: Array<String>) {

    //region Any
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tokens

        if (!tokens.contentEquals(other.tokens)) return false

        return true
    }

    override fun hashCode(): Int {
        return tokens.contentHashCode()
    }
    //endregion
}