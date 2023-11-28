package com.example.roomhw.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.roomhw.db.Entity
import com.example.roomhw.repo.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: Repository
) : BaseViewModel() {
    val listLiveData: LiveData<List<Entity?>> = repo.itemFlow.asLiveData()

    fun saveItem(todo: String) {
        launch(
            request = {
                repo.saveTodo(todo)
            }
        )
    }

    fun deleteById(id: Int) {
        launch(
            request = {
                repo.deleteById(id)
            }
        )
    }
    fun putMark(id: Int) {
        launch(
            request = {
                repo.putMark(id)
            }
        )
    }
    fun getCount(){
        launch(
            request = {
                repo.getCount()
            }
        )
    }
}

abstract class BaseViewModel: ViewModel() {
    private val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

    private var _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private var _exceptionLiveData = MutableLiveData<String?>()
    val exceptionLiveData: LiveData<String?> = _exceptionLiveData

    fun <T> launch(
        request: suspend () -> T,
        onSuccess: (T) -> Unit = { }
    ) {
        coroutineScope.launch {
            try {
                _loadingLiveData.postValue(true)
                val response = request.invoke()
                onSuccess.invoke(response)
            } catch (e: Exception) {
                _exceptionLiveData.postValue(e.message)
                Log.e(">>>", e.message.orEmpty())
            } finally {
                _loadingLiveData.postValue(false)
            }
        }
    }
}