package com.example.presentation.model

import com.example.domain.model.Carousel
import com.example.domain.model.Product
import com.example.presentation.delegate.ProductDelegate

class CarouselVM(model: Carousel, private val productDelegate: ProductDelegate): PresentationVM<Carousel>(model) {

    fun openCarouselProduct(product: Product) {
        productDelegate.openProduct(product)
        sendCarouselLog()
    }
    private fun sendCarouselLog() {

    }
}