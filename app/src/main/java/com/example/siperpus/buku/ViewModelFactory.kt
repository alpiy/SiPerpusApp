package com.example.siperpus.buku

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.siperpus.config.ApiClient
import com.example.siperpus.config.ApiService


class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory {
            if (instance == null) {
                val apiService = ApiClient.getApiClient().create(ApiService::class.java)
                instance = ViewModelFactory(Repository(apiService))
            }
            return instance as ViewModelFactory
        }
    }
}


//class ViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                MainViewModel() as T
//            }
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//        @JvmStatic
//        fun getInstance(): ViewModelFactory {
//            if (INSTANCE == null) {
//                synchronized(ViewModelFactory::class.java) {
//                    INSTANCE = ViewModelFactory()
//                }
//            }
//            return INSTANCE as ViewModelFactory
//        }
//    }
//}