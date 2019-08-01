package com.movie.films.mvp.model.entity

import com.google.gson.annotations.SerializedName

class User {

    @SerializedName("id")
    val id: Int? =0

    @SerializedName("username")
    val username: String? = null

    @SerializedName("created_at")
    val createdAt: Long? =0

    @SerializedName("updated_at")
    val updatedAt: Long? =0

}