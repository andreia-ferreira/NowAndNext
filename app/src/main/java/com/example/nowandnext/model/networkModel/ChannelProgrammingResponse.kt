package com.example.nowandnext.model.networkModel

import com.example.nowandnext.model.networkModel.ChannelProgramming
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class ChannelProgrammingResponse {

    @SerializedName("odata.metadata")
    @Expose
    var odataMetadata: String? = null

    @SerializedName("value")
    @Expose
    var value: List<ChannelProgramming?>? = null

}