package com.example.nowandnext.utils.map

import com.example.nowandnext.model.networkModel.Channel
import com.example.nowandnext.model.networkModel.ChannelProgrammingResponse
import com.example.nowandnext.model.DisplayProgram

object DisplayProgramMapper {

    fun mapToDisplayProgram(channel: Channel, programmingResponse: ChannelProgrammingResponse): DisplayProgram {
        val formatProgramName = programmingResponse.value?.get(0)?.title?.replace(" ", "+")
        return DisplayProgram(
            programmingResponse.value?.get(0)?.callLetter ?: "",
            "http://proxycache.app.iptv.telecom.pt:8080/eemstb/ImageHandler.ashx?evTitle=$formatProgramName&chCallLetter=${channel.callLetter}&profile=16_9&width=320",
            channel.title ?: "",
            channel.channelPosition ?: 0,
            programmingResponse.value?.get(0)?.title ?: "",
            programmingResponse.value?.get(1)?.title ?: ""
        )
    }
}
