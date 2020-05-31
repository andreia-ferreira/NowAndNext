package com.example.nowandnext.utils.map

interface IMapper<I, O> {
    fun map(input: I): O
}