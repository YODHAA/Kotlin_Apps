package com.example.fragment_component.data


import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("author")
    val author: String, // IGV- News Desk Team
    @SerializedName("content")
    val content: String, // NEW DELHI: Former Pakistan pacer Shoaib Akhtar on Wednesday proposed a made-for-television three-match ODI series against arch-rivals India to raise funds for the fight against the COVID-19 pandemic in both the countries. The two nations have not played a f… [+3308 chars]
    @SerializedName("description")
    val description: String, // NEW DELHI: Former Pakistan pacer Shoaib Akhtar on Wednesday proposed a made-for-television three-match ODI series against arch-rivals India to raise funds for the fight against the COVID-19 pandemic i
    @SerializedName("publishedAt")
    val publishedAt: String, // 2020-04-08T18:35:46Z
    @SerializedName("source")
    val source: Source,
    @SerializedName("title")
    val title: String, // Shoaib Akhtar proposes Indo-Pak series to raise funds for fight against COVID-19 pandemic - India Gone Viral
    @SerializedName("url")
    val url: String, // https://indiagoneviral.com/sports/2020/04/08/shoaib-akhtar-proposes-indo-pak-series-to-raise-funds-for-fight-against-covid-19-pandemic/17115/india-gone-viral/
    @SerializedName("urlToImage")
    val urlToImage: String // https://indiagoneviral.com/wp-content/uploads/2020/04/17115/shoaib-akhtar-proposes-indo-pak-series-to-raise-funds-for-fight-against-covid-19-pandemic.jpg
)