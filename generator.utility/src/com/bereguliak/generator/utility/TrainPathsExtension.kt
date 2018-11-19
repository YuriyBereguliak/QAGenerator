package com.bereguliak.generator.utility

import java.io.File

const val FOLDER_MODELS = "models"
const val FOLDER_TRAIN = "train"

const val FILE_SENTENCE_DETECTOR_TRAIN_DATA = "sentence-detector-train.txt"
const val FILE_TOKENIZER_DETECTOR_TRAIN_DATA = "tokenizer-detector-train.txt"
const val FILE_NER_NAME_TRAIN_DATA = "ner-name-train.txt"
const val FILE_CHUNKER_TRAIN_DATA = "chunker-train.txt"
const val FILE_POS_TRAIN_DATA = "part-of-speech-train.txt"

const val FILE_SENTENCE_MODEL = "sentence-model.bin"
const val FILE_TOKENIZER_MODEL = "tokenizer-model.bin"
const val FILE_NER_NAME_MODEL = "ner-name-model.bin"
const val FILE_CHUNKER_MODEL = "chunker-model.bin"
const val FILE_POS_MODEL = "part-of-speech-model.bin"

//region Training models - *.txt
fun getTokenizerDetectorTrainPath() = FILE_TOKENIZER_DETECTOR_TRAIN_DATA.getTrainModelsFilePath()

fun getSentenceDetectorTrainPath() = FILE_SENTENCE_DETECTOR_TRAIN_DATA.getTrainModelsFilePath()

fun getNerNameTrainPath() = FILE_NER_NAME_TRAIN_DATA.getTrainModelsFilePath()

fun getChunkerTrainPath() = FILE_CHUNKER_TRAIN_DATA.getTrainModelsFilePath()

fun getPosTrainPath() = FILE_POS_TRAIN_DATA.getTrainModelsFilePath()
//endregion

//region Trained Models - *.bin
fun getTokenizerBinModelPath() = FILE_TOKENIZER_MODEL.getModelsFilePath()

fun getPosBinModelPath() = FILE_POS_MODEL.getModelsFilePath()

fun getSentenceBinModelPath() = FILE_SENTENCE_MODEL.getModelsFilePath()

fun getNerNameBinModelPath() = FILE_NER_NAME_MODEL.getModelsFilePath()

fun getChunkerModelPath() = FILE_CHUNKER_MODEL.getModelsFilePath()
//endregion

//region Utility API
private fun String.getTrainModelsFilePath() = FOLDER_MODELS + File.separator + FOLDER_TRAIN + File.separator + this

private fun String.getModelsFilePath() = FOLDER_MODELS + File.separator + this
//endregion