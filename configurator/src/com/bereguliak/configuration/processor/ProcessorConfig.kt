package com.bereguliak.configuration.processor

data class ProcessorConfig(val sentenceModelPath: String,
                           val tokenizerModelPath: String,
                           val nerModelPath: String,
                           val chunkerModelPath: String,
                           val posModelPath: String) {

    private constructor(builder: Builder) : this(builder.sentenceModelPath,
            builder.tokenizerModelPath,
            builder.nerModelPath,
            builder.chunkerModelPath,
            builder.posModelPath)

    //region Utility structures
    companion object {
        inline fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var sentenceModelPath: String = ""
        var tokenizerModelPath: String = ""
        var nerModelPath: String = ""
        var chunkerModelPath: String = ""
        var posModelPath: String = ""

        fun build() = ProcessorConfig(this)
    }
    //endregion
}