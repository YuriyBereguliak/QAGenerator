package com.bereguliak.processor.generator.processor.cleaner

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.DataChain

class CleanerChain : BaseGeneratorChain() {

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region BaseGeneratorChain
    override fun handle(data: DataChain): DataChain {
        val result = data.sourceText
                .replace("[^0-9\\-+*?=&%$§!^#:;\\\\\",_²³°\\[\\]{}<>|~]", " ")
                .replace(",", " ")
                .replace("-", " ")
                .replace(" – ", " ")
                .replace(" –", " ")
                .replace("–", " ")
                .replace(" — ", " ")
                .replace("...", " ")
        data.sourceText = result
        endTime(data.sourceText.length)
        return handleNext(data)
    }
    //endregion

    //region Utility structures
    companion object {
        private const val TAG = "NoiseCleaner"
    }
    //endregion
}