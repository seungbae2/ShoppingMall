package com.example.presentation.viewmodel.purchase_history

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.PurchaseHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PurchaseHistoryViewModel @Inject constructor(
    private val useCase: PurchaseHistoryUseCase
): ViewModel()  {

    val purchaseHistory = useCase.getPurchaseHistory()
}