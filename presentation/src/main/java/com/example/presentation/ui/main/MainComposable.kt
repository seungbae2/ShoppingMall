package com.example.presentation.ui.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.GridItemSpan
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.domain.model.*
import com.example.presentation.ui.component.*
import com.example.presentation.viewmodel.MainViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainComposable(viewModel : MainViewModel) {
    val modelList by viewModel.modelList.collectAsState(initial = listOf())
    val columnCount by viewModel.columnCount.collectAsState()

    LazyVerticalGrid(cells = GridCells.Fixed(columnCount)) {
        items(modelList.size, span = { index ->
            val item = modelList[index]
            val spanCount = getSpanCountByType(item.type, columnCount)

            GridItemSpan(spanCount)
        }) {
            when (val item = modelList[it]) {
                is Banner -> BannerCard(model = item) { model ->
                    viewModel.openBanner(model)
                }

                is BannerList -> BannerListCard(model = item) { model ->
                    viewModel.openBannerList(model)
                }

                is Product -> ProductCard(product = item) { model ->
                    viewModel.openProduct(model)
                }

                is Carousel -> CarouselCard(model = item) { model ->
                    viewModel.openCarouselProduct(model)
                }
                is Ranking -> RankingCard(model = item) { model ->
                    viewModel.openRankingProduct(model)
                }
            }
        }
    }
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int) : Int {
    return when(type) {
        ModelType.PRODUCT -> 1
        ModelType.BANNER, ModelType.BANNER_LIST,
        ModelType.CAROUSEL, ModelType.RANKING -> defaultColumnCount
    }
}