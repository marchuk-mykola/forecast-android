package com.marchuk.app.core.utils.mappers

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

interface NullableListMapper<I, O> : Mapper<List<I>?, List<O>?>

interface Mapper<I, O> {
    operator fun invoke(input: I): O
}