package com.example.presentation.model

import androidx.navigation.NavHostController
import com.example.domain.model.Product
import com.example.domain.model.Ranking
import com.example.presentation.delegate.ProductDelegate

class RankingVM(model: Ranking, private val productDelegate: ProductDelegate) : PresentationVM<Ranking>(model) {
    fun openRankingProduct(navHostController: NavHostController, product: Product) {
        productDelegate.openProduct(navHostController, product)
        sendRankingLog()
        // +@
    }

    fun likeProduct(product: Product) {
        productDelegate.likeProduct(product)
    }


    private fun sendRankingLog() {

    }
}