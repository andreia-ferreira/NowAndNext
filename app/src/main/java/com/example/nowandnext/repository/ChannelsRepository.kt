package com.example.nowandnext.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nowandnext.R
import com.example.nowandnext.database.ProgramsDao
import com.example.nowandnext.network.RetrofitHelper
import com.example.nowandnext.model.Channel
import com.example.nowandnext.model.ChannelsResponse
import com.example.nowandnext.model.DisplayProgram
import com.example.nowandnext.utils.map.DisplayProgramMapper
import retrofit2.Response
import java.net.UnknownHostException

class ChannelsRepository(
    private val application: Application,
    private val dao: ProgramsDao,
    private val retrofitHelper: RetrofitHelper) {

    val reposErrors = MutableLiveData<String>()
    val listToDisplay = MutableLiveData<List<DisplayProgram>>()
    private val networkList = mutableListOf<DisplayProgram>()
    private var nextUrl = MutableLiveData<String?>()

    suspend fun refreshChannels(reset: Boolean): Boolean {

        try {
            val response = if (reset) {
                loadInitialChannels()
            } else {
                loadMoreChannels()
            }

            if (response.isSuccessful) {
                val listChannels = response.body()?.value ?: emptyList()
                nextUrl.value = response.body()?.odataNextLink

                if (listChannels.isNotEmpty()) {
                    Log.d(TAG, "Found ${listChannels.size} results")

                    for (channel: Channel? in listChannels) {
                        channel?.let {
                            getChannelProgramming(it)
                        }
                    }
                    dao.insertDisplayPrograms(networkList)
                    reposErrors.value = null
                } else {
                    reposErrors.value = application.getString(R.string.error_empty_search_channel)
                }
            }
        } catch (e: UnknownHostException) {
            reposErrors.value = application.getString(R.string.error_service_call)
        } catch (e: Exception) {
            Log.e(TAG, "$e: ${e.message}")

        } finally {
            return true
        }
    }

    private suspend fun loadInitialChannels(): Response<ChannelsResponse?> {
        val response = retrofitHelper.getChannels()

        if (response.isSuccessful) {
            clearCache()
        }

        return response
    }

    private suspend fun loadMoreChannels(): Response<ChannelsResponse?> {
        if (nextUrl.value == null) throw Exception("End")
        return retrofitHelper.getMoreChannels(nextUrl.value!!)
    }

    private suspend fun getChannelProgramming(channel: Channel) {

        try {
            val response = retrofitHelper.getChannelProgramming(channel.callLetter ?: "")

            if (response.isSuccessful) {
                val programmingResponse = response.body()
                if (programmingResponse != null) {
                    Log.d(TAG, "Found ${programmingResponse.value?.size} results")

                    val displayProgram = DisplayProgramMapper.mapToDisplayProgram(channel, programmingResponse)

                    networkList.add(displayProgram)

                    reposErrors.value = null
                } else {
                    reposErrors.value = application.getString(R.string.error_empty_search_program)
                }
            }
        } catch (e: UnknownHostException) {
            reposErrors.value = application.getString(R.string.error_service_call)
        } catch (e: Exception) {
            Log.e(TAG, "$e: ${e.message}")
        }
    }

    suspend fun getStoredPrograms() {
        try {
            listToDisplay.value = dao.getDisplayPrograms()
            Log.d(TAG, "Fetched ${listToDisplay.value?.size} items")

        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
    }

    private suspend fun clearCache() {
        dao.deleteAllPrograms()
        listToDisplay.value = emptyList()
        networkList.clear()
        nextUrl.value = null
    }

    companion object {
        private val TAG = ChannelsRepository::class.java.simpleName

        // For Singleton instantiation
        @Volatile
        private var instance: ChannelsRepository? = null

        fun getInstance(application: Application, dao: ProgramsDao, retrofitHelper: RetrofitHelper) =
            instance ?: synchronized(this) {
                instance ?: ChannelsRepository(application, dao, retrofitHelper).also { instance = it }
            }
    }

}