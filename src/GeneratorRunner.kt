import com.bereguliak.generator.TextGeneration
import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.listeners.OnTextGeneratorResult

fun main(args: Array<String>) {
    val textGeneration = TextGeneration(object : OnTextGeneratorResult {
        override fun onResult(data: ReaderChunk) {
            System.out.println("Result :: $data")
        }
    })
    textGeneration.runTextGenerator("Це є мій текст")
    textGeneration.runTextGenerator("Це є мій текст номер 2")
    textGeneration.runTextGenerator("Це є мій текст номер 4")
}