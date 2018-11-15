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
    val sentence = "Комп'ютерна мережа система зв'язку між двома чи більше комп'ютерами. застосовується у разі " +
            " Комп'ютерна мережа тестування складних програмних комплексів. " +
            " Основними методами реінжинірингу є такі." +
            " оціночна функція є для того, щоб прийти до однозначного і по можливості найвигіднішого варіанту рішення навіть." +
            " Матриця рішень доповнюється ще одним стовпчиком із найменших результатів еir кожного рядка." +
            " Критерій Севіджа маємо розгялнути більш детально." +
            " Оскільки Матриця рішень будується завжди." +
            " Також необхідно враховувати Критерій Байеса - Лапласа він є важливим при проведені обчислень." +
            " Щодо застосування класичних критеріїв , вони є важливі." +
            " Статистичні методи закінчення тестування. " +
            " Підходи  Мутації , Мутанти , Метод мутаційного тестування . " +
            " На даному кроці формується Мутаційний критерій. " +
            " Стохастичне тестування застосовується у випадку тестуваня складних програм. " +
            " Структурні критерії. Тестування є дуже важливим. " +
            " Верифікація забезпечує відповідність. " +
            " Атестація гарантує програмний продукт виконує вимоги. " +
            " Мінімаксний критерій  використовує оціночну функцію. " +
            " Тестування правил - це набір критеріїв. " +
            " Системи штучного інтелекту широко застосовуються."

    textGeneration.runTextGenerator(sentence)
}