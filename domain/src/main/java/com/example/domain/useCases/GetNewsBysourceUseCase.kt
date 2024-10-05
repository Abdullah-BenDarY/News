package com.example.domain.useCases

import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class GetNewsBysourceUseCase @Inject constructor (private val newsRepo: NewsRepo) {

    suspend fun invoke(source: String) = newsRepo.getNewsBySource(source)

}