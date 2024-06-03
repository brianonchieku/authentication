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

}