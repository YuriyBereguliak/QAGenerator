import com.bereguliak.generator.TextGeneration
import com.bereguliak.generator.model.entity.ReaderChunk
import com.bereguliak.generator.model.listeners.OnTextGeneratorResult
import com.bereguliak.generator.utility.log

fun main(args: Array<String>) {
    val textGeneration = TextGeneration(object : OnTextGeneratorResult {
        override fun onResult(data: ReaderChunk) {
            data.sentences.log("Sentence")
            data.tokens.log("Tokens")
            data.ner.log("NER theses")
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