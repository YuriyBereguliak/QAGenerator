import com.bereguliak.generator.train.MainTrainContainer
import com.bereguliak.generator.utility.logWithOffset

fun main(args: Array<String>) {
    "Start training models ".logWithOffset()

    val trainer = MainTrainContainer()
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