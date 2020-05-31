package com.penguin.thebooklore.utils.mapper

import com.example.nowandnext.utils.map.IMapper

// Nullable to not null
interface INullableInputListMapper<I, O>: IMapper<List<I>?, List<O>>

open class INullableInputListMapperImpl<I, O>(
        private val mapper: IMapper<I, O>) : INullableInputListMapper<I, O> {
    override fun map(input: List<I>?): List<O> {
        return input?.map { mapper.map(it) }.orEmpty()
    }
}
