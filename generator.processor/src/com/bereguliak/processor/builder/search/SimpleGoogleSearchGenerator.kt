package com.bereguliak.processor.builder.search

import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.net.SearchData
import org.jsoup.Jsoup


class SimpleGoogleSearchGenerator(data: DataChain) : BaseBuilder<List<SearchData>>(data) {
    //region BaseBuilder
    override fun generate(): List<SearchData> {
        startTime()

        val resultList = mutableListOf<SearchData>()
        data.ner.distinct().forEach { ner ->
            val searchURL = "$GOOGLE_SEARCH_URL$QUESTION$ner$NUMBER$GOOGLE_SEARCH_RESULT_FOR_ITEM"
            val doc = Jsoup.connect(searchURL).userAgent(MOZILLA_AGENT).get()
            val results = doc.select(CSS_QUERY)

            results.forEach { result ->
                val linkHref = result.attr(ATTRIBUTE_KEY)
                val linkText = result.text()
                resultList.add(SearchData(ner, linkText, linkHref.buildUrl()))
            }
        }

        endTime(data.ner.size)
        return resultList.toList()
    }
    //endregion

    //region TimeHandler
    override fun methodName() = TAG
    //endregion

    //region Utility API
    private fun String.buildUrl() = substring(6, indexOf(CONCAT))
    //endregion

    //region Utility structure
    companion object {
        private const val GOOGLE_SEARCH_URL = "https://www.google.com/search"
        private const val QUESTION = "?q="
        private const val NUMBER = "&num="
        private const val GOOGLE_SEARCH_RESULT_FOR_ITEM = 5
        private const val CSS_QUERY = "h3.r > a"
        private const val ATTRIBUTE_KEY = "href"
        private const val MOZILLA_AGENT = "Mozilla/5.0"
        private const val CONCAT = "&"

        private const val TAG = "UrlSearch"
    }
    //endregion
}