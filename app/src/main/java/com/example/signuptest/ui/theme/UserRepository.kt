package com.example.signuptest.ui.theme

import androidx.lifecycle.LiveData
import androidx.room.Query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(private val usersDao: UsersDao) {

    val readAllData: LiveData<List<Users>> = usersDao.readAllData()

    suspend fun insertUser(users: Users) {
        usersDao.insertUser(users)
    }

    suspend fun getUserByEmail(email: String): Users? {
        return usersDao.getUserByEmail(email)
    }

    suspend fun getUser(username: String, password: String): Users? {
        return usersDao.getUser(username, password)
    }

    suspend fun updateUser(user: Users) {
        usersDao.updateUser(user)

    }
    suspend fun checkSecurityQuestionAnswer(question: String, answer: String): Users?{
       return usersDao.checkSecurityQuestionAnswer(question, answer)

    }

}