package com.example.domain.useCases

import com.example.domain.repoisitories.LatestNewsRepo
import javax.inject.Inject

class GetLatestNewsUseCase @Inject constructor(private val latestNewsRepo: LatestNewsRepo) {

    suspend fun invoke(country: String) = latestNewsRepo.getLatestNews(country)

}