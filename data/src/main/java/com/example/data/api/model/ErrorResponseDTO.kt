package com.example.data.api.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.models.ErrorResponse
import com.example.domain.models.LNews
import com.google.gson.annotations.SerializedName

@Parcelize
data class ErrorResponseDTO(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
