package com.android.e_commercecompose.Screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.e_commercecompose.model.State
import com.android.e_commercecompose.repository.CommerceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CommerceRepository
) : ViewModel() {

    private val _categoriesLiveData = MutableLiveData<State<Response<List<String>>>>()
    val categoriesLiveData: LiveData<State<Response<List<String>>>> get() = _categoriesLiveData


    init {
        getCategoryList()
    }

    private fun getCategoryList() = viewModelScope.launch{

        _categoriesLiveData.postValue(State.Loading())
        _categoriesLiveData.value = State.Success(repository.getCategories())

    }

    override fun onCleared() {
        super.onCleared()
        println("onCleared run")
    }
}