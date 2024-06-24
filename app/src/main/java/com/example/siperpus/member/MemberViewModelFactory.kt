package com.example.siperpus.member

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MemberViewModelFactory () : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MemberViewModel::class.java) -> {
                MemberViewModel() as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: MemberViewModelFactory? = null
        @JvmStatic
        fun getInstance(): MemberViewModelFactory {
            if (INSTANCE == null) {
                synchronized(MemberViewModelFactory::class.java) {
                    INSTANCE = MemberViewModelFactory()
                }
            }
            return INSTANCE as MemberViewModelFactory
        }
    }
}