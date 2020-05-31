package com.example.nowandnext.model.networkModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class ChannelProgramming {
    @SerializedName("Id")
    @Expose
    var id: String? = null

    @SerializedName("CallLetter")
    @Expose
    var callLetter: String? = null

    @SerializedName("StartDate")
    @Expose
    var startDate: String? = null

    @SerializedName("StartTime")
    @Expose
    var startTime: String? = null

    @SerializedName("ProgramId")
    @Expose
    var programId: Int? = null

    @SerializedName("EndDate")
    @Expose
    var endDate: String? = null

    @SerializedName("Title")
    @Expose
    var title: String? = null

    @SerializedName("Synopsis")
    @Expose
    var synopsis: String? = null

    @SerializedName("SeriesId")
    @Expose
    var seriesId: Any? = null

    @SerializedName("SeriesEpisodeNumber")
    @Expose
    var seriesEpisodeNumber: Any? = null

    @SerializedName("Participants")
    @Expose
    var participants: Any? = null

    @SerializedName("ImageUri")
    @Expose
    var imageUri: Any? = null

    @SerializedName("IsAdultContent")
    @Expose
    var isAdultContent: Boolean? = null

    @SerializedName("IsEnabled")
    @Expose
    var isEnabled: Boolean? = null

    @SerializedName("SearchRank")
    @Expose
    var searchRank: Int? = null

    @SerializedName("NumberOfEpisodes")
    @Expose
    var numberOfEpisodes: Any? = null

    @SerializedName("IsLiveAnytimeChannel")
    @Expose
    var isLiveAnytimeChannel: Boolean? = null

    @SerializedName("TitleId")
    @Expose
    var titleId: String? = null

    @SerializedName("IsBlackout")
    @Expose
    var isBlackout: Boolean? = null

}