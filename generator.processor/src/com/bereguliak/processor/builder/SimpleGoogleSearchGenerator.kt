package com.bereguliak.processor.builder

import com.bereguliak.processor.builder.core.BaseBuilder
import com.bereguliak.processor.model.entity.DataChain
import com.bereguliak.processor.model.entity.net.SearchData
import org.jsoup.Jsoup


class SimpleGoogleSearchGenerator(data: DataChain) : BaseBuilder<List<SearchData>>(data) {
    //region BaseBuilder
    override fun generate(): List<SearchData> {
        val searchTerm = "Twitter"

        val searchURL = "$GOOGLE_SEARCH_URL?q=$searchTerm&num=$10"

        val doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get()

        val results = doc.select("h3.r > a")

        val resultList = mutableListOf<SearchData>()
        for (result in results) {
            val linkHref = result.attr("href")
            val linkText = result.text()
            resultList.add(SearchData(linkText, linkHref.substring(6, linkHref.indexOf("&"))))
        }
        return resultList.toList()
    }
    //endregion

    companion object {
        const val GOOGLE_SEARCH_URL = "https://www.google.com/search"
    }
}