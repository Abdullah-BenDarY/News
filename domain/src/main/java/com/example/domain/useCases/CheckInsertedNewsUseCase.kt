package com.example.domain.useCases

import androidx.lifecycle.LiveData
import com.example.domain.models.LNews
import com.example.domain.repoisitories.NewsRepo
import javax.inject.Inject
class CheckInsertedNewsUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun invoke(id: Int): Boolean {
        return newsRepo.isNewsSaved(id)
    }
}

