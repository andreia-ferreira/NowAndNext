package com.example.nowandnext.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "program_table")
data class DisplayProgram(
        @PrimaryKey
        var callLetter: String,
        var image: String,
        var channelName: String,
        var channelPosition: Int,
        var currentProgram: String,
        var nextProgram: String)