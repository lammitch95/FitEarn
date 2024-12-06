package com.example.fitearn.ui


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.fitearn.data.dao.UserDao
import com.example.fitearn.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserProfileScreenViewModel(private val userDao: UserDao) : ViewModel() {

    companion object {
        fun provideFactory(userDao: UserDao): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(UserProfileScreenViewModel::class.java)) {
                        return UserProfileScreenViewModel(userDao) as T
                    }
                    throw IllegalArgumentException("Unknown ViewModel class")
                }
            }
        }
    }

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _errorState = MutableStateFlow("")
    val errorState: StateFlow<String> = _errorState

    fun fetchUser(email: String) {
        viewModelScope.launch {
            try {
                val user = userDao.checkIfEmailExists(email)
                if (user != null) {
                    _user.value = user
                } else {
                    _errorState.value = "User not found"
                }
            } catch (e: Exception) {
                _errorState.value = e.message ?: "Unknown error occurred"
            }
        }
    }
}



//
//class UserProfileScreenViewModel(private val appDatabase: AppDatabase): ViewModel() {
//
//    companion object {
//        fun provideFactory(appDatabase: AppDatabase): ViewModelProvider.Factory {
//            return object : ViewModelProvider.Factory {
//                @Suppress("UNCHECKED_CAST")
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    if (modelClass.isAssignableFrom(UserProfileScreenViewModel::class.java)) {
//                        return UserProfileScreenViewModel(appDatabase) as T
//                    }
//                    throw IllegalArgumentException("Unknown ViewModel class")
//                }
//            }
//        }
//    }
//
//    var firstNameState = mutableStateOf("")
//        private set
//    var lastNameState = mutableStateOf("")
//        private set
//    var phoneNumberState = mutableStateOf("")
//        private set
//    var emailState = mutableStateOf("")
//        private set
//    var weightState = mutableStateOf("")
//        private set
//    var dateOfBirthState = mutableStateOf("")
//        private set
//
//    var errorState = mutableStateOf("")
//        private set
//
//    fun fetchUserProfile(onError: (String) -> Unit) {
//        viewModelScope.launch {
//            fetchUserFromAuth(
//                onSuccess = { user ->
//                    firstNameState.value = user.firstName
//                    lastNameState.value = user.lastName
//                    phoneNumberState.value = user.phoneNumber
//                    emailState.value = user.email
//                    weightState.value = user.weight
//                    dateOfBirthState.value = user.dateOfBirth
//                },
//                onError = { error ->
//                    errorState.value = error.message ?: "Unknown error occurred"
//                    onError(errorState.value)
//                }
//            )
//        }
//    }
//}