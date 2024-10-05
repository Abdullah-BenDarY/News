package com.example.data.api.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.domain.models.ModelNewsSource
import com.google.gson.annotations.SerializedName

@Parcelize
data class ModelNewsSource(

	@field:SerializedName("sources")
	val sources: List<SourcesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class SourcesItem(

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("category")
	val category: String? = null,

	@field:SerializedName("url")
	val url: String? = null
) : Parcelable{
	fun toNewsSource(): ModelNewsSource {
		return ModelNewsSource(
			country = country,
			name = name,
			description = description,
			language = language,
			id = id,
			category = category,
			url = url
		)
	}
}

