package com.example.data.datasource

import com.example.data.model.TestModelResponse
import javax.inject.Inject

class TestDataSource @Inject constructor() {

    fun getTestModelResponse() : TestModelResponse {
        return TestModelResponse("response")
    }
}