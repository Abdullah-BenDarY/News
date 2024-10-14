package com.example.domain.useCases

import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class GetOfflineNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun invoke() = newsRepo.getOfflineNews()

}