package com.movie.films.mvp.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movie: Serializable {

    @SerializedName("id")
    val id: Int? = 0

    @SerializedName("name")
    val name: String? = null

    @SerializedName("year")
    val year: Int? = 0

    @SerializedName("thumbnail")
    val thumbnail: String? = null

    @SerializedName("director")
    val director: String? = null

    @SerializedName("main_star")
    val mainStar: String? = null

    @SerializedName("description")
    val description: String? = null

    @SerializedName("created_at")
    val createdAt: String? = null

    @SerializedName("updated_at")
    val updatedAt: String? = null

    @SerializedName("url")
    val url: String? = null

    @SerializedName("gentres")
    var gentres: List<Genre>? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as Movie?
        return id == that!!.id
    }

    fun getGentresArr(): ArrayList<Genre>{
        return ArrayList(gentres)
    }



}