package com.bereguliak.generator.utility

import java.io.File

const val FOLDER_MODELS = "models"
const val FOLDER_TRAIN = "train"

const val FILE_NER_NAME_TRAIN_DATA = "ner-name-train.txt"

const val FILE_SENTENCE_MODEL = "sentence-model.bin"
const val FILE_NER_NAME_MODEL = "ner-name-model.bin"

fun getTrainPathName() = FILE_NER_NAME_TRAIN_DATA.getTrainModelsFilePath()

fun getSentenceBinModelPath() = FILE_SENTENCE_MODEL.getModelsFilePath()

fun getNerNameBinModelPath() = FILE_NER_NAME_MODEL.getModelsFilePath()

//region Utility API
private fun String.getTrainModelsFilePath() = FOLDER_MODELS + File.separator + FOLDER_TRAIN + File.separator + this

private fun String.getModelsFilePath() = FOLDER_MODELS + File.separator + this
//endregion