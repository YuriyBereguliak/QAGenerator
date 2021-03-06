package com.bereguliak.configuration.train

data class TrainConfig(val sentenceModelPath: String,
                       val tokenizerModelPath: String,
                       val nerModelPath: String,
                       val chunkerModelPath: String,
                       val posModelPath: String,
                       val pluralModelPath: String,
                       val sentenceTrainPath: String,
                       val tokenizerTrainPath: String,
                       val nerTrainPath: String,
                       val chunkerTrainPath: String,
                       val posTrainPath: String,
                       val pluralTrainPath: String) {

    private constructor(builder: Builder) : this(builder.sentenceModelPath,
            builder.tokenizerModelPath,
            builder.nerModelPath,
            builder.chunkerModelPath,
            builder.posModelPath,
            builder.pluralModelPath,
            builder.sentenceTrainPath,
            builder.tokenizerTrainPath,
            builder.nerTrainPath,
            builder.chunkerTrainPath,
            builder.posTrainPath,
            builder.pluralTrainPath)

    //region Utility structures
    companion object {
        fun build(block: Builder.() -> Unit) = Builder().apply(block).build()
    }

    class Builder {
        var sentenceModelPath: String = ""
        var tokenizerModelPath: String = ""
        var nerModelPath: String = ""
        var chunkerModelPath: String = ""
        var posModelPath: String = ""
        var pluralModelPath: String = ""

        var sentenceTrainPath: String = ""
        var tokenizerTrainPath: String = ""
        var nerTrainPath: String = ""
        var chunkerTrainPath: String = ""
        var posTrainPath: String = ""
        var pluralTrainPath: String = ""

        fun build() = TrainConfig(this)
    }
    //endregion
}