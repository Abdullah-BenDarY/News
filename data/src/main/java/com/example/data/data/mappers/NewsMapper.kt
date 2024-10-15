package com.example.data.data.mappers

import com.example.data.data.model.ArticlesItem
import com.example.domain.models.LNews
    fun toData(news: LNews): ArticlesItem {
        return ArticlesItem(
            publishedAt = news.publishedAt,
            author = news.author,
            urlToImage = news.urlToImage,
            description = news.description,
            title = news.title,
            url = news.url,
            content = news.content,
            id = news.id?:1,
            isSaved = news.isSaved
        )
    }

