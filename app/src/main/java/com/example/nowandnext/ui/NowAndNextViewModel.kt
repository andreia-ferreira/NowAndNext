package com.example.nowandnext.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nowandnext.database.ProgramsDao
import com.example.nowandnext.database.ProgramsDatabase
import com.example.nowandnext.network.RetrofitHelper
import com.example.nowandnext.model.Channel
import com.example.nowandnext.model.DisplayProgram
import com.example.nowandnext.repository.ChannelsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class NowAndNextViewModel(private val mApplication: Application) : AndroidViewModel(mApplication) {

    private val retrofitHelper: RetrofitHelper by lazy { RetrofitHelper.getInstance(mApplication) }
    private val repository: ChannelsRepository by lazy { ChannelsRepository.getInstance(mApplication, ProgramsDatabase.getDatabase(mApplication).museumDao(), retrofitHelper) }
    val listDisplayProgram: LiveData<List<DisplayProgram>> = repository.listToDisplay
    val isError = repository.reposErrors
    var job: Job? = null
    var nextItems: Int = 0
    val isLoading = MutableLiveData(false)
    fun showLoading() { isLoading.value = true }
    fun hideLoading() { isLoading.value = false }

    fun refreshCollection() {

        job = viewModelScope.launch {
            val completed = async { repository.refreshChannels(nextItems) }

            if (completed.await()) {
                repository.getStoredPrograms()
            }

        }
    }

    companion object {
        private val TAG = NowAndNextViewModel::class.java.simpleName
    }

}