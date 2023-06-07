package com.example.data.datasource

import com.example.data.model.TestModelResponse

class TestDataSource {

    fun getTestModelResponse() : TestModelResponse {
        return TestModelResponse("response")
    }
}