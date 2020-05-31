package com.example.nowandnext.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.nowandnext.R
import com.example.nowandnext.database.ProgramsDao
import com.example.nowandnext.network.RetrofitHelper
import com.example.nowandnext.model.Channel
import com.example.nowandnext.model.DisplayProgram
import com.example.nowandnext.utils.map.DisplayProgramMapper
import java.net.UnknownHostException

class ChannelsRepository(
    private val application: Application,
    private val dao: ProgramsDao,
    private val retrofitHelper: RetrofitHelper) {

    val reposErrors = MutableLiveData<String>()
    val listToDisplay = MutableLiveData<List<DisplayProgram>>()
    private val networkList = mutableListOf<DisplayProgram>()

    suspend fun refreshChannels(nextItems: Int): Boolean {

        try {
            val response = retrofitHelper.getChannels(nextItems)

            if (response.isSuccessful) {
                val listChannels = response.body()?.value ?: emptyList()
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