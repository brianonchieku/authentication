package com.example.signuptest.ui.theme

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UsersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: Users)

    @Query("SELECT * FROM Users WHERE email = :email")
    suspend fun getUserByEmail(email: String): Users?

    @Query("SELECT * FROM Users ORDER BY id ASC")
    fun readAllData(): LiveData<List<Users>>

    @Query("SELECT * FROM Users WHERE username = :username AND password = :password")
    suspend fun getUser(username: String, password: String): Users?

    @Update
    suspend fun updateUser(user: Users)

    @Query("SELECT * FROM Users WHERE question = :question AND answer = :answer")
    suspend fun checkSecurityQuestionAnswer(question: String, answer: String): Users?



}