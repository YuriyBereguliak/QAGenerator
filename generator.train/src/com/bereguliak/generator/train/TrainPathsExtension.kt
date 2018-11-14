package com.bereguliak.generator.train

import com.bereguliak.generator.train.MainTrainContainer.Companion.FILE_NER_MODEL
import com.bereguliak.generator.train.MainTrainContainer.Companion.FILE_SENTENCE_MODEL
import com.bereguliak.generator.train.MainTrainContainer.Companion.FILE_TOKENIZER_MODEL
import com.bereguliak.generator.train.MainTrainContainer.Companion.FILE_TRAIN_DATA
import com.bereguliak.generator.train.MainTrainContainer.Companion.FOLDER_MODELS
import com.bereguliak.generator.train.MainTrainContainer.Companion.FOLDER_TRAIN
import java.io.File

fun getTrainPathName() = FILE_TRAIN_DATA.getTrainModelsFilePath()

fun getSentenceBinModelPath() = FILE_SENTENCE_MODEL.getModelsFilePath()

fun getNerBinModelPath() = FILE_NER_MODEL.getModelsFilePath()

fun getTokenizerBinModel() = FILE_TOKENIZER_MODEL.getModelsFilePath()

//region Utility API
private fun String.getTrainModelsFilePath() = FOLDER_MODELS + File.separator + FOLDER_TRAIN + File.separator + this

private fun String.getModelsFilePath() = FOLDER_MODELS + File.separator + this
//endregion