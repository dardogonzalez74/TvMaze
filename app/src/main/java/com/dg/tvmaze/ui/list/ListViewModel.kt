package com.dg.tvmaze.ui.list

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dg.tvmaze.entities.Show
import com.dg.tvmaze.extensions.asLiveData
import com.dg.tvmaze.extensions.toResult
import com.dg.tvmaze.ui.main.log
import com.dg.tvmaze.usecases.RetrieveShowUseCase
import kotlinx.coroutines.launch

@SuppressLint("NullSafeMutableLiveData") // Lint is not processing well <T:Any> from toResult
class ListViewModel(
    private val retrieveShowUC: RetrieveShowUseCase
): ViewModel() {

    private val _show = MutableLiveData<Result<Show>>()
    val show = _show.asLiveData()

    fun getShow(id: Int) {
        viewModelScope.launch {
            _show.value = toResult { retrieveShowUC.byId(id) }
        }
    }
}