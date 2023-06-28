package com.example.presentation.model

import com.example.domain.model.Product
import com.example.presentation.delegate.ProductDelegate

class ProductVM(model: Product, productDelegate: ProductDelegate):
    PresentationVM(model), ProductDelegate by productDelegate