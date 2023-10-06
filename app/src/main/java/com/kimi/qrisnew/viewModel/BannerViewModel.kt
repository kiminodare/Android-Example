package com.kimi.qrisnew.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kimi.qrisnew.model.BannerItem
import com.kimi.qrisnew.model.DataOrException
import com.kimi.qrisnew.repository.BannerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BannerViewModel @Inject constructor(
    private val repository: BannerRepository
) : ViewModel() {

    val DataBanner: MutableState<DataOrException<ArrayList<BannerItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(null, true, Exception(""))
        )


    init {
        getAllBanner()
    }

    private fun getAllBanner() {
        viewModelScope.launch {
            DataBanner.value.isLoading = true
            DataBanner.value = repository.getAllBanner()
            if (DataBanner.value.data.toString().isNotEmpty()) {
                DataBanner.value.isLoading = false
            }
        }
    }
}