package com.example.nowandnext.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChannelsResponse {
    @SerializedName("odata.metadata")
    @Expose
    var odataMetadata: String? = null

    @SerializedName("odata.count")
    @Expose
    var odataCount: String? = null

    @SerializedName("value")
    @Expose
    var value: List<Channel?>? = null

    @SerializedName("odata.nextLink")
    @Expose
    var odataNextLink: String? = null
}