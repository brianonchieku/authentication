package com.example.signuptest.ui.theme

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    private val _loginResult = MutableLiveData<Result<Users>>()
    val loginResult: LiveData<Result<Users>> get() = _loginResult

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    fun addUser(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertUser(user)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = repository.getUser(username, password)
            _loginResult.value = if (user != null) {
                Result.success(user)
            } else {
                Result.failure(Exception("Invalid username or password"))
            }
        }
    }
    fun userLogin(user: Users) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)

        }
    }

    fun getUser(userText: String, passText: String) {
        viewModelScope.launch {
            val user = repository.getUser(userText, passText)
            if (user != null) {
                _loginResult.value = Result.success(user)
            } else {
                _loginResult.value = Result.failure(Exception("User not found"))
            }
        }

    }

    fun checkSecurityQuestionAnswer(question: String, answer: String): LiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        viewModelScope.launch(Dispatchers.IO) {
            val user = repository.checkSecurityQuestionAnswer(question, answer)
            result.postValue(user != null)
        }
        return result
    }
}