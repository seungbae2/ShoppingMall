package com.example.data.model

import com.example.domain.model.TestModel

class TestModelResponse(val name: String?)

fun TestModelResponse.toDomainModel(): TestModel? {
    if(name != null) {
        return TestModel(name)
    }
    return null
}