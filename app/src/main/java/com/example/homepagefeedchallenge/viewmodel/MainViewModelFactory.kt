package com.example.homepagefeedchallenge.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homepagefeedchallenge.data.repository.HomePageRepository

class MainViewModelFactory(private val repository: HomePageRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}