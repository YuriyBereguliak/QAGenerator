package com.bereguliak.configuration

data class Config(val sentenceModelPath: String,
                  val tokenizerModelPath: String,
                  val nerModelPath: String,
                  val chunkerModelPath: String,
                  val posModelPath: String)