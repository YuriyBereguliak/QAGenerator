import com.bereguliak.generator.TextGeneration
import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.listeners.OnTextGeneratorResult
import com.bereguliak.generator.train.MainTrainContainer

fun main(args: Array<String>) {
    val trainer = MainTrainContainer()
    trainer.trainSentenceDetectionModel()
    trainer.trainNerDetectionModel()

    val textGeneration = TextGeneration(object : OnTextGeneratorResult {
        override fun onResult(data: ReaderChunk) {
            System.out.println("Result :: $data")
        }
    })
    textGeneration.runTextGenerator("Це є мій текст. Це є мій текст номер 2. Це є мій текст номер 4.")
}