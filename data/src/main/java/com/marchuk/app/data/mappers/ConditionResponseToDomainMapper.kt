package com.marchuk.app.data.mappers

import com.marchuk.app.core.utils.mappers.Mapper
import com.marchuk.app.data.models.ConditionResponse
import com.marchuk.app.domain.models.Condition

internal class ConditionResponseToDomainMapper : Mapper<ConditionResponse, Condition> {

    override fun invoke(input: ConditionResponse): Condition =
        Condition(
            iconUrl = input.icon,
            text = input.text
        )


}