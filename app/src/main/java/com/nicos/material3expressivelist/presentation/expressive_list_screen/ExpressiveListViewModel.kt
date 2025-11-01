package com.nicos.material3expressivelist.presentation.expressive_list_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@HiltViewModel
class ExpressiveListViewModel @Inject constructor(
    private val createExpressiveListDataModel: CreateExpressiveListDataModel
) : ViewModel() {

    private val _items = MutableStateFlow<MutableList<ExpressiveListDataModel>>(mutableListOf())
    val items: MutableStateFlow<MutableList<ExpressiveListDataModel>> = _items

    init {
        viewModelScope.launch {
            createExpressiveListDataModel.createExpressiveListDataModel()
                .flowOn(Dispatchers.IO)
                .collect {
                    viewModelScope.launch(Dispatchers.Main) {
                        _items.value = it
                    }
                }
        }
    }
}