package com.example.presentation.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.SearchFilter
import com.example.presentation.ui.component.ProductCard
import com.example.presentation.ui.theme.Purple200
import com.example.presentation.ui.theme.Purple500
import com.example.presentation.viewmodel.search.SearchViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
    navHostController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchFilter by viewModel.searchFilters.collectAsState()
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    var currentFilterType by remember { mutableStateOf<SearchFilter.Type?>(null) }

    BottomSheetScaffold(
        scaffoldState= scaffoldState,
        sheetShape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
        sheetContent = {

        },
        sheetPeekHeight = 0.dp
    ) {
        SearchContent(viewModel, navHostController) {
            scope.launch {
                currentFilterType = it
                sheetState.expand()
            }
        }
    }
}

@Composable
fun SearchFilterCategoryContent(filter: SearchFilter.CategoryFilter, onCompleteFilter:(SearchFilter) -> Unit) {
    //헤더
    //카테고리 리스트

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        Text(
            text = "카테고리 필터",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier.padding(10.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(10.dp)
        ) {
            items(filter.categories.size) { index ->
                val category = filter.categories[index]
                Button(
                    onClick = {
                        filter.selectedCategory = category
                        onCompleteFilter(filter)
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = if(filter.selectedCategory == category) Purple500
                    else Purple200))
                {
                    Text(
                        fontSize = 18.sp,
                        text = category.categoryName
                    )
                }

            }
        }
    }
}

@Composable
fun SearchContent(
    viewModel: SearchViewModel,
    navHostController: NavHostController,
    openFilterDialog: (SearchFilter.Type) -> Unit
) {
    val searchResult by viewModel.searchResult.collectAsState()
    val searchKeywords by viewModel.searchKeywords.collectAsState(listOf())
    var keyword by remember { mutableStateOf("") }

    Column {
        SearchBox(
            keyword = keyword,
            onValueChange = { keyword = it },
            searchAction = {
                viewModel.search(keyword)
            }
        )

        if(searchResult.isEmpty()) {
            Text(
                modifier = Modifier.padding(6.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = "최근 검색어"
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(searchKeywords.size) { index ->
                    val currentKeyword = searchKeywords.reversed()[index].keyword
                    Button(
                        onClick = {
                            keyword = currentKeyword
                            viewModel.search(keyword)
                        },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified)
                    ) {
                        Text(
                            fontSize = 18.sp,
                            text = currentKeyword
                        )
                    }
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(10.dp)
            ) {
                items(searchResult.size) {index ->
                    ProductCard(navHostController = navHostController, presentationVM = searchResult[index])
                }
            }
        }
    }
}

@Composable
fun SearchBox(
    keyword: String,
    onValueChange: (String) -> Unit,
    searchAction: () -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        TextField(
            value = keyword,
            onValueChange = onValueChange,
            placeholder = { Text(text="검색어를 입력해 주세요") },
            shape = RoundedCornerShape(size = 8.dp),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
                autoCorrect = true
            ),
            keyboardActions = KeyboardActions(
                onSearch = { searchAction() }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            maxLines = 1,
            singleLine = true,
            leadingIcon = { Icon(Icons.Filled.Search, "SearchIcon") }
        )
    }
}