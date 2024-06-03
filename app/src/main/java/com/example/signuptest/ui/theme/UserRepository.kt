package com.example.signuptest.ui.theme

import androidx.lifecycle.LiveData

class UserRepository(private val usersDao: UsersDao) {

    val readAllData: LiveData<List<Users>> = usersDao.readAllData()

    suspend fun insertUser(users: Users) {
        usersDao.insertUser(users)
    }

    suspend fun getUserByEmail(email: String): Users? {
        return usersDao.getUserByEmail(email)
    }
}