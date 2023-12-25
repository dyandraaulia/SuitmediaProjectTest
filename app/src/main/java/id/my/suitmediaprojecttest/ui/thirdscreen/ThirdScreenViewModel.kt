package id.my.suitmediaprojecttest.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.my.suitmediaprojecttest.data.remote.response.DataItem
import id.my.suitmediaprojecttest.data.repository.Repository

class ThirdScreenViewModel(repository: Repository) : ViewModel() {
    val users: LiveData<PagingData<DataItem>> =
        repository.getUsers().cachedIn(viewModelScope)
}