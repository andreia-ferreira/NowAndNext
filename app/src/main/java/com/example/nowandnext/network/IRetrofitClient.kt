package com.example.nowandnext.network

import com.example.nowandnext.model.ChannelProgramming
import com.example.nowandnext.model.ChannelProgrammingResponse
import com.example.nowandnext.model.ChannelsResponse
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface IRetrofitClient {

    @GET("/catalog/v7/Channels")
    suspend fun getChannels(@Query("UserAgent") agent: String,
                            @Query("\$filter", encoded = true) filter: String,
                            @Query("\$orderby", encoded = true) orderBy: String,
                            @Query("\$inlinecount", encoded = true) inlineCount: String)
            : Response<ChannelsResponse?>

    @GET
    suspend fun getMoreChannels(@Url nextUrl: String)
            : Response<ChannelsResponse?>

    @GET("/Program/v7/Programs/NowAndNextLiveChannelPrograms")
    suspend fun getChannelProgramming(@Query("UserAgent") agent: String,
                            @Query("\$filter", encoded = true) filter: String,
                            @Query("\$orderby", encoded = true) orderBy: String)
            : Response<ChannelProgrammingResponse?>

    @GET("ImageHandler.ashx")
    suspend fun getProgramPicture(@Query("evTitle") programName: String,
                                  @Query("chCallLetter") channelName: String,
                                  @Query("profile") ratio: String,
                                  @Query("width") width: Int)
            : Response<ChannelProgrammingResponse?>
}
