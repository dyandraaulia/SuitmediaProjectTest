package id.my.suitmediaprojecttest.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import id.my.suitmediaprojecttest.data.paging.UserPagingSource
import id.my.suitmediaprojecttest.data.remote.response.DataItem
import id.my.suitmediaprojecttest.data.remote.retrofit.ApiService

class Repository private constructor(
    private val apiService: ApiService,
) {

    fun getUsers(): LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(apiService)
        }.also { instance = it }
    }
}