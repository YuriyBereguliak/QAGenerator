package com.bereguliak.configuration

class GeneratorConfiguration {

    var sentenceModelPath: String = ""
    var tokenizerModelPath: String = ""
    var nerModelPath: String = ""
    var chunkerModelPath: String = ""
    var posModelPath: String = ""

    //region GeneratorConfiguration
    fun build(): Config {
        return Config(sentenceModelPath, tokenizerModelPath, nerModelPath, chunkerModelPath, posModelPath)
    }
    //endregion
}