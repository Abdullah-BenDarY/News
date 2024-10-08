package com.example.domain.models
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LNews(
	val publishedAt: String? = null,
	val author: String? = null,
	val urlToImage: String? = null,
	val description: String? = null,
	val title: String? = null,
	val url: String? = null,
	val content: String? = null,
	val source : String? = null,
) : Parcelable
