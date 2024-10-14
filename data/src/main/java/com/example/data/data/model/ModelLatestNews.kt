package com.example.data.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.LNews
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelLatestNews(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable

@Parcelize
data class Source(
	@field:SerializedName("name")
 	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Parcelable

@Parcelize
@Entity
data class ArticlesItem(
	@ColumnInfo
	@field:SerializedName("publishedAt")
	val publishedAt: String? = null,

	@ColumnInfo
	@field:SerializedName("author")
	val author: String? = null,

	@ColumnInfo
	@field:SerializedName("urlToImage")
	val urlToImage: String? = null,

	@ColumnInfo
	@field:SerializedName("description")
	val description: String? = null,

	@ColumnInfo
	@field:SerializedName("source")
	val source: Source? = null,

	@ColumnInfo
	@field:SerializedName("title")
	val title: String? = null,

	@ColumnInfo
	@field:SerializedName("url")
	val url: String? = null,

	@ColumnInfo
	@field:SerializedName("content")
	val content: String? = null,

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo("id", index = true)
	val id: Int
) : Parcelable {

	fun toLNews(): LNews {
		return LNews(
			publishedAt = publishedAt,
			author = author,
			urlToImage = urlToImage,
			description = description,
			title = title,
			url = url,
			content = content,
			id = id
		)
	}
}
//	fun toData(news: LNews): ArticlesItem {
//		return ArticlesItem(
//			publishedAt = news.publishedAt,
//			author = news.author,
//			urlToImage = news.urlToImage,
//			description = news.description,
//			title = news.title,
//			url = news.url,
//			content = news.content,
//			id = news.id?:1
//		)
//	}

