package com.example.domain.useCases

import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject

class GetNewsSourceUseCase @Inject constructor (private val NewsRepo: NewsRepo) {

    suspend fun invoke(category: String) = NewsRepo.getNewsSource(category)
}