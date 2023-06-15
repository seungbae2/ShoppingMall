package com.example.presentation.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.presentation.ui.common.ProductCard
import com.example.presentation.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel : MainViewModel) {
    val productList by viewModel.productList.collectAsState(initial = listOf())

    LazyColumn {
        items(productList.size) {
            ProductCard(product = productList[it]) {
                // 상세화면 개발시 추가
            }
        }
    }
}