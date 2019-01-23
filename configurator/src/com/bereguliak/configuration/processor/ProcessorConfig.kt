package com.bereguliak.configuration.processor

data class ProcessorConfig(val sentenceModelPath: String,
                           val tokenizerModelPath: String,
                           val nerModelPath: String,
                           val chunkerModelPath: String,
                           val posModelPath: String,
                           val pluralModelPath: String) {

    private constructor(builder: Builder) : this(builder.sentenceModelPath,
            builder.tokenizerModelPath,
            builder.nerModelPath,
            builder.chunkerModelPath,
            builder.posModelPath,
            builder.pluralModelPath)

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
        var pluralModelPath: String = ""

        fun build() = ProcessorConfig(this)
    }
    //endregion
}