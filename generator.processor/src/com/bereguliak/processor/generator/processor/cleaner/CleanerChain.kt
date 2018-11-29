package com.bereguliak.processor.generator.processor.cleaner

import com.bereguliak.processor.generator.core.chain.BaseGeneratorChain
import com.bereguliak.processor.model.entity.DataChain

class CleanerChain : BaseGeneratorChain() {
    //region BaseGeneratorChain
    override fun handle(data: DataChain): DataChain {
        val result = data.sourceText
                .replace(",", " ")
                .replace("-", " ")
                .replace(" – ", " ")
                .replace(" –", " ")
                .replace("–", " ")
                .replace(" — ", " ")
                .replace("...", " ")
        data.sourceText = result
        return handleNext(data)
    }
    //endregion
}