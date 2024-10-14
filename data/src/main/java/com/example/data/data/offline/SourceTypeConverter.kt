package com.example.data.data.offline
import androidx.room.TypeConverter
import com.example.data.data.model.Source
import com.google.gson.Gson

class SourceTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSource(source: Source?): String? {
        return source?.let { gson.toJson(it) }
    }

    @TypeConverter
    fun toSource(sourceString: String?): Source? {
        return sourceString?.let { gson.fromJson(it, Source::class.java) }
    }
}
