import com.bereguliak.configuration.*
import com.bereguliak.configuration.train.TrainConfig
import com.bereguliak.generator.train.MainTrainContainer
import com.bereguliak.generator.utility.logWithOffset

fun main(args: Array<String>) {
    "Start training models ".logWithOffset()

    val config = TrainConfig.build {
        sentenceModelPath = getSentenceBinModelPath()
        sentenceTrainPath = getSentenceDetectorTrainPath()

        tokenizerModelPath = getTokenizerBinModelPath()
        tokenizerTrainPath = getTokenizerDetectorTrainPath()

        nerModelPath = getNerNameBinModelPath()
        nerTrainPath = getNerNameTrainPath()

        chunkerModelPath = getChunkerModelPath()
        chunkerTrainPath = getChunkerTrainPath()

        posModelPath = getPosBinModelPath()
        posTrainPath = getPosTrainPath()
    }

    val trainer = MainTrainContainer(config)
    "Training SentenceDetector model".logWithOffset()
    trainer.trainSentenceDetectionModel()

    "Training NER model".logWithOffset()
    trainer.trainNerDetectionModel()

    "Training Tokenizer model".logWithOffset()
    trainer.trainTokenizerModel()

//    "Training Part of speech model".logWithOffset()
//    trainer.trainPosModel()

//    "Training Chunker model".logWithOffset()
//    trainer.trainChunkerModel()
}