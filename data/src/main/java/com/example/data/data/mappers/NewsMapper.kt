package com.example.data.data.mappers

import com.example.data.data.model.ArticlesItem
import com.example.domain.models.LNews

class NewsMapper {
    fun toDomain(articlesItem: ArticlesItem): LNews {
        return LNews(
            // map fields from ArticlesItem to LNews
        )
    }

    fun toData(news: LNews): ArticlesItem {
        return ArticlesItem(
            publishedAt = news.publishedAt,
            author = news.author,
            urlToImage = news.urlToImage,
            description = news.description,
            title = news.title,
            url = news.url,
            content = news.content
        )
    }
}

