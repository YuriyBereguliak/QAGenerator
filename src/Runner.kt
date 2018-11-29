import com.bereguliak.configuration.*
import com.bereguliak.generator.utility.log
import com.bereguliak.generator.utility.logWithOffset
import com.bereguliak.processor.builder.SimpleQuestionGenerator
import com.bereguliak.processor.builder.TheseGenerator
import com.bereguliak.processor.generator.TextGeneration
import com.bereguliak.processor.model.entity.ReaderChunk
import com.bereguliak.processor.model.listeners.OnTextGeneratorResult

fun main(args: Array<String>) {
    val generatorConfiguration = GeneratorConfiguration()
    generatorConfiguration.tokenizerModelPath = getTokenizerBinModelPath()
    generatorConfiguration.sentenceModelPath = getSentenceBinModelPath()
    generatorConfiguration.chunkerModelPath = getChunkerModelPath()
    generatorConfiguration.nerModelPath = getNerNameBinModelPath()
    generatorConfiguration.posModelPath = getPosBinModelPath()
    val config = generatorConfiguration.build()

    val textGeneration = TextGeneration(object : OnTextGeneratorResult {
        override fun onResult(data: ReaderChunk) {
            data.sentences.log("Sentence")
            data.tokens.log("Tokens")

            val theses = TheseGenerator(data).generate()
            theses.title.logWithOffset()
            theses.theses.log()

            val questions = SimpleQuestionGenerator(data).generate()
            questions.title.logWithOffset()
            questions.text.log()
        }
    })

    val sentence = "Комп'ютерна мережа — система зв'язку між двома чи більше комп'ютерами ." +
            " Наступним кроком на шляху дослідження підходів, методів і технологій реінжинірингу. " +
            " Тестування правил - це набір критеріїв. " +
            " Системи штучного інтелекту широко застосовуються." +
            " Мутації – дрібні помилки в програмі." +
            " Мутанти – програми, що відрізняються одна від іншої мутаціями. " +
            " Тестування функцій – набір тестів у сукупності повинен забезпечити перевірку кожної дії. " +
            " Критерій повинен бути достатнім  , тобто показувати, коли деяка скінченна множина тестів достатня. " +
            " Оцінка результатів виконання програми на наборі тестів з метою ухвалення рішення про продовження або зупинку тестування. "

    textGeneration.runTextGenerator(sentence)
}