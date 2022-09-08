package com.dg.tvmaze.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dg.tvmaze.extensions.toLiveData

class ListViewModel: ViewModel() {

    private val _name = MutableLiveData<String>()
    val name = _name.toLiveData()

    init {
        _name.value = "List"
    }
}