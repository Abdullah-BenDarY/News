package com.example.domain.useCases

import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class GetSearchQueryUseCase @Inject constructor (private val newsRepo: NewsRepo) {

    suspend fun invoke(query: String) = newsRepo.getSearchQuery(query)

}