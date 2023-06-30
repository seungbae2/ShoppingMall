package com.example.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.domain.model.Product
import com.example.domain.model.Ranking
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.example.presentation.R
import com.example.presentation.model.RankingVM

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(navHostController: NavHostController, presentationVM : RankingVM) {
    val pagerState = rememberPagerState()
    val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT

    Column {
        Text(
            text = presentationVM.model.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        )
        HorizontalPager(
            count = pageCount,
            state = pagerState,
            contentPadding = PaddingValues(end = 50.dp)
        ) {index ->
            Column {
                RankingProductCard(index * 3 , presentationVM.model.productList[index * 3], presentationVM) { product->
                    presentationVM.openRankingProduct(navHostController, product)
                }
                RankingProductCard(index * 3 + 1 , presentationVM.model.productList[index * 3 + 1], presentationVM) { product ->
                    presentationVM.openRankingProduct(navHostController, product)
                }
                RankingProductCard(index * 3 + 2 , presentationVM.model.productList[index * 3 + 2], presentationVM) { product ->
                    presentationVM.openRankingProduct(navHostController, product)
                }
            }
        }
    }
}

@Composable
fun RankingProductCard(index: Int, product: Product, presentationVM: RankingVM, onClick: (Product) -> Unit) {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Text(
            "${index + 1}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 0.dp, 10.dp, 0.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.product_image),
            "description",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(0.7f)
        )
        Column(
            modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Text(
                fontSize = 14.sp,
                text = product.shop.shopName,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Text(
                fontSize = 14.sp,
                text = product.productName,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
            )
            Price(product = product)
        }
    }

}

private const val DEFAULT_RANKING_ITEM_COUNT = 3