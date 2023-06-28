package com.example.presentation.delegate

import androidx.navigation.NavHostController
import com.example.domain.model.Category

interface CategoryDelegate {
    fun openCategory(navHostController: NavHostController, category: Category)
}