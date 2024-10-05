package com.example.domain.useCases

import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class GetLatestNewsUseCase @Inject constructor (private val latestNewsRepo: NewsRepo) {

    suspend fun invoke(title: String) = latestNewsRepo.getLatestNews(title)

}