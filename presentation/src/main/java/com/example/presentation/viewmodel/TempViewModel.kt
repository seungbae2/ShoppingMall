package com.example.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.TestModel
import com.example.domain.usecase.TestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TempViewModel @Inject constructor(private val useCase: TestUseCase): ViewModel() {

    fun getTempModel() : TestModel? {
        return useCase.getTestModel()

    }
}