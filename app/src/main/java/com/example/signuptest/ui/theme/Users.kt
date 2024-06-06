package com.example.signuptest.ui.theme

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
   @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val surname: String,
    val otherNames: String,
    val userName: String,
    val password: String,
    val email: String,
    val phone: String,
    val question: String,
    val answer: String
)
