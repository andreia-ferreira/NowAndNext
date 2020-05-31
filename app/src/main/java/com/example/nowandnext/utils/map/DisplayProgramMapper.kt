package com.example.nowandnext.utils.map

import com.example.nowandnext.model.Channel
import com.example.nowandnext.model.ChannelProgramming
import com.example.nowandnext.model.ChannelProgrammingResponse
import com.example.nowandnext.model.DisplayProgram
import com.penguin.thebooklore.utils.mapper.INullableInputListMapperImpl

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
