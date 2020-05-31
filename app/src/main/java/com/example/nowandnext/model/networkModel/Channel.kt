package com.example.nowandnext.model.networkModel

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class Channel {
    @SerializedName("Id")
    @Expose
    var id: Int? = null

    @SerializedName("Title")
    @Expose
    var title: String? = null

    @SerializedName("IsAdult")
    @Expose
    var isAdult: Boolean? = null

    @SerializedName("InPromotion")
    @Expose
    var inPromotion: Boolean? = null

    @SerializedName("PromotionDescription")
    @Expose
    var promotionDescription: Any? = null

    @SerializedName("ProductKey")
    @Expose
    var productKey: String? = null

    @SerializedName("ParentalRating")
    @Expose
    var parentalRating: Any? = null

    @SerializedName("CatalogPrice")
    @Expose
    var catalogPrice: String? = null

    @SerializedName("AvailableOnChannels")
    @Expose
    var availableOnChannels: String? = null

    @SerializedName("Thematic")
    @Expose
    var thematic: String? = null

    @SerializedName("Description")
    @Expose
    var description: String? = null

    @SerializedName("CatalogOrderNumber")
    @Expose
    var catalogOrderNumber: Int? = null

    @SerializedName("DeviceSubscription")
    @Expose
    var deviceSubscription: Boolean? = null

    @SerializedName("PresentationKey")
    @Expose
    var presentationKey: String? = null

    @SerializedName("IsSpecialPromotion")
    @Expose
    var isSpecialPromotion: Boolean? = null

    @SerializedName("CommercialKey")
    @Expose
    var commercialKey: String? = null

    @SerializedName("ImageQuality")
    @Expose
    var imageQuality: String? = null

    @SerializedName("PromotionPrice")
    @Expose
    var promotionPrice: String? = null

    @SerializedName("PromotionTagLine")
    @Expose
    var promotionTagLine: String? = null

    @SerializedName("RetentionDescription")
    @Expose
    var retentionDescription: String? = null

    @SerializedName("ExclusiveContent")
    @Expose
    var exclusiveContent: Boolean? = null

    @SerializedName("RestartTV")
    @Expose
    var restartTV: Boolean? = null

    @SerializedName("HasL2Vs")
    @Expose
    var hasL2Vs: Boolean? = null

    @SerializedName("Interactive")
    @Expose
    var interactive: Boolean? = null

    @SerializedName("Region")
    @Expose
    var region: String? = null

    @SerializedName("CallLetter")
    @Expose
    var callLetter: String? = null

    @SerializedName("ChannelPosition")
    @Expose
    var channelPosition: Int? = null

    @SerializedName("Language")
    @Expose
    var language: String? = null

    @SerializedName("Subtitled")
    @Expose
    var subtitled: Boolean? = null

    @SerializedName("MinimumSubscriptionDays")
    @Expose
    var minimumSubscriptionDays: Int? = null

    @SerializedName("IsLiveAnyTime")
    @Expose
    var isLiveAnyTime: Boolean? = null

    @SerializedName("FriendlyUrlName")
    @Expose
    var friendlyUrlName: String? = null
}