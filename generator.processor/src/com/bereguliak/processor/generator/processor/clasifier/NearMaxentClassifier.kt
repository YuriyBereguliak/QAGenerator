package com.bereguliak.processor.generator.processor.clasifier

import com.bereguliak.generator.utility.log
import com.bereguliak.maxent.model.MaxentDataModel
import com.bereguliak.processor.generator.processor.clasifier.core.BaseClassifier
import com.bereguliak.processor.model.entity.DataChain

class NearMaxentClassifier : BaseClassifier() {

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region BaseGeneratorChain
    override fun handle(data: DataChain): DataChain {
        val mutableListOf = mutableListOf(
                MaxentDataModel("Комп'ютер", "Комп'ютер"),
                MaxentDataModel("штучний інтелект", "Системи штучного інтелекту"),
                MaxentDataModel("штучний інтелект", "штучний інтелект"),
                MaxentDataModel("штучний інтелект", "Штучний інтелект"),
                MaxentDataModel("Мережі", "Мережі"),
                MaxentDataModel("комутатор", "комутатор"),
                MaxentDataModel("комутатор", "Комутатор"),
                MaxentDataModel("комутатор мережевий пристрій", "комутатор мережевий пристрій"),
                MaxentDataModel("ЕОМ", "електронно обчислювальна машина"),
                MaxentDataModel("ЕОМ", "електронна обчислювальна машина")
        )
        buildModel(mutableListOf)

        data.ner.forEach {
            val result = eval(it)
            if (result.isNotEmpty()) {
                data.classifier.add(result)
            }
        }

        data.sentences.forEach {
            val result = eval(it.text)
            if (result.isNotEmpty()) {
                data.classifier.add(result)
            }
        }
        return handleNext(data)
    }
    //endregion

    //region Utility structures
    companion object {
        private const val TAG = "NearMaxent"
    }
    //endregion
}