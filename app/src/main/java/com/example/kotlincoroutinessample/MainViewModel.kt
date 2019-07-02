package com.example.kotlincoroutinessample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _snackBar = MutableLiveData<String>()
    val snackbar: LiveData<String>
        get() = _snackBar

    fun onMainViewClicked() {
        uiScope.launch {
            delay(1000)
            _snackBar.postValue("Hello, from threads!")
        }
    }

    fun onSnackbarShown() {
        _snackBar.value = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
