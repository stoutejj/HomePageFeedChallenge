package com.example.homepagefeedchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.homepagefeedchallenge.data.model.Page
import com.example.homepagefeedchallenge.data.repository.HomePageRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: HomePageRepository) : ViewModel() {
    private val disposable = CompositeDisposable()
    private val _state = MutableLiveData<MainViewState>()
    val state: LiveData<MainViewState>
        get() = _state

    fun fetchHomePage() {
        _state.postValue(MainViewState.Loading)
        disposable.add(
            repository.fetchHomePage()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response ->
                    if (response.page.cards.isEmpty()) {
                        _state.postValue(
                            MainViewState.Error("No Data Found")
                        )
                    } else {
                        _state.postValue(MainViewState.Success(response.page))
                    }
                }, { throwable ->
                    _state.postValue(
                        MainViewState.Error(
                            throwable.localizedMessage ?: "Unknown Error"
                        )
                    )
                })
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    sealed class MainViewState {
        object Loading : MainViewState()
        data class Success(val page: Page) : MainViewState()
        data class Error(val errorMessage: String) : MainViewState()
    }

}