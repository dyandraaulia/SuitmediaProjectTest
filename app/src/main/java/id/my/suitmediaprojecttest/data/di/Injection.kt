package id.my.suitmediaprojecttest.data.di

import android.content.Context
import id.my.suitmediaprojecttest.data.remote.retrofit.ApiConfig
import id.my.suitmediaprojecttest.data.repository.Repository

object Injection {
    fun provideRepository(context: Context): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}