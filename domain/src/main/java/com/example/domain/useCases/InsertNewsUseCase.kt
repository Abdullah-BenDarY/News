package com.example.domain.useCases

import com.example.domain.models.LNews
import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class InsertNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun invoke(news: LNews) = newsRepo.insertNews(news)

}