package com.skyapp.newsapp.data.remote.dto.article

import com.google.gson.annotations.SerializedName

/*  api Response Reference

{
    "count": 31757,
    "next": "https://api.spaceflightnewsapi.net/v4/articles/?limit=10&offset=10",
    "previous": null,
    "results": [
    {
        "id": 35398,
        "title": "Coverage, Briefing Set for NASA’s Artemis II Moon Rocket Roll to Pad",
        "authors": [
        {
            "name": "NASA",
            "socials": null
        }
        ],
        "url": "https://www.nasa.gov/news-release/coverage-briefing-set-for-nasas-artemis-ii-moon-rocket-roll-to-pad/",
        "image_url": "https://www.nasa.gov/wp-content/uploads/2026/01/artemisii.jpg",
        "news_site": "NASA",
        "summary": "NASA’s integrated SLS (Space Launch System) rocket and Orion spacecraft for the Artemis II mission is inching closer to launch – literally. The agency is targeting no earlier than 7 a.m. EST, Saturday, Jan. 17, to begin the multi-hour trek from the Vehicle Assembly Building to Launch Pad 39B at NASA’s Kennedy Space Center in […]",
        "published_at": "2026-01-14T22:26:49Z",
        "updated_at": "2026-01-14T22:30:10.497603Z",
        "featured": false,
        "launches": [],
        "events": []
    },

 */



data class NewsArticleResponseDto(
    val count : Int?,
    val next : String?,
    val previous : String?,
    val results : List<NewsArticleItemDto>

)


data class NewsArticleItemDto(
    val id : Int?,
    val title : String?,
    val authors : List<AuthorDto>,
    val url : String?,
    @SerializedName("image_url")
    val imageUrl : String?,
    @SerializedName("news_site")
    val newsSite : String?,

    val summary : String?,

    @SerializedName("published_at")
    val publishedAt : String?,

    @SerializedName("updated_at")
    val updatedAt : String?,
    val featured : Boolean?,
    val launches : List<Any>,
    val events : List<Any>
)

data class AuthorDto(
    val name: String?,
    val socials: Any?
)