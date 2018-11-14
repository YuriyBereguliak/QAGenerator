package com.bereguliak.generator.utility

import java.io.File

const val FOLDER_MODELS = "models"
const val FOLDER_TRAIN = "train"

const val FILE_TRAIN_DATA = "UkraineTrainData.txt"
const val FILE_SENTENCE_MODEL = "sentence-model.bin"
const val FILE_TOKENIZER_MODEL = "tokenizer-model.bin"

const val FILE_NER_MODEL = "ner-model.bin"

fun getTrainPathName() = FILE_TRAIN_DATA.getTrainModelsFilePath()

fun getSentenceBinModelPath() = FILE_SENTENCE_MODEL.getModelsFilePath()

fun getNerBinModelPath() = FILE_NER_MODEL.getModelsFilePath()

fun getTokenizerBinModel() = FILE_TOKENIZER_MODEL.getModelsFilePath()

//region Utility API
private fun String.getTrainModelsFilePath() = FOLDER_MODELS + File.separator + FOLDER_TRAIN + File.separator + this

private fun String.getModelsFilePath() = FOLDER_MODELS + File.separator + this
//endregion