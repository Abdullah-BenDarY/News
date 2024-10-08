package com.example.domain.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelNewsSource(
    val country: String? = null,
    val name: String? = null,
    val description: String? = null,
    val language: String? = null,
    val id: String? = null,
    val category: String? = null,
    val url: String? = null
) : Parcelable