package com.example.internatura


import com.google.gson.annotations.SerializedName

data class CommentResponse(
    @SerializedName("photos")
    val photos: Photos,
    @SerializedName("stat")
    val stat: String
)