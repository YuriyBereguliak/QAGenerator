import com.bereguliak.processor.generator.processor.clasifier.NearMaxEatClassifier
import com.bereguliak.processor.model.entity.ReaderChunk

fun main(args: Array<String>) {
    NearMaxEatClassifier().handle(ReaderChunk(""))
}