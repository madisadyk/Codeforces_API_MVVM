package com.madisadyk.codeforcesapimvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "handlers"
)
data class CFHandler(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val avatar: String,
    val city: String,
    val contribution: Int,
    val country: String,
    val firstName: String,
    val friendOfCount: Int,
    val handle: String,
    val lastName: String,
    val lastOnlineTimeSeconds: Int,
    val maxRank: String,
    val maxRating: Int,
    val organization: String,
    val rank: String,
    val rating: Int,
    val registrationTimeSeconds: Int,
    val titlePhoto: String
): Serializable