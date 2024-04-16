package com.bytesthetic.shoesapp.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bytesthetic.shoesapp.component.CategoriesList
import com.bytesthetic.shoesapp.component.ProductHorizontalList
import com.bytesthetic.shoesapp.component.ProductSmallCard
import com.bytesthetic.shoesapp.mock.generateProducts
import com.bytesthetic.shoesapp.model.ProductUiModel
import com.bytesthetic.shoesapp.navigation.BottomNavigationBar
import com.bytesthetic.shoesapp.theme.Accent
import com.bytesthetic.shoesapp.theme.DarkText
import com.bytesthetic.shoesapp.theme.MediumText
import com.bytesthetic.shoesapp.theme.ShoesAppTheme
import com.bytesthetic.shoesapp.viewmodel.SharedViewModel

@Composable
fun HomeScreen(
    viewModel: SharedViewModel,
    navController: NavController
) {
    HomeContent(
        onProductClick = { product ->
            viewModel.selectProduct(product)
            navController.navigate(PRODUCT_DETAILS_SCREEN)
        }
    )
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    onProductClick: (ProductUiModel) -> Unit
) {
    val productList by remember { mutableStateOf(generateProducts()) }
    var searchText by remember { mutableStateOf("") }
    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 20.dp)
    ) {
        item {
            TextField(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 16.dp)
                    .height(68.dp)
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(start = 18.dp)
                    .border(1.dp, Color.Gray, RoundedCornerShape(16.dp)),
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text(text = "Search") },
                textStyle = TextStyle(color = MediumText, fontSize = 16.sp)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 34.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Speacial Offers",
                    color = DarkText,
                    fontSize = 12.sp
                )

                Text(
                    text = "See All",
                    color = Accent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        item {
            CategoriesList(
                modifier = Modifier
                    .padding(top = 28.dp)
            )
        }

        item {
            ProductHorizontalList(
                modifier = Modifier
                    .padding(top = 22.dp),
                productList = productList,
                onProductClick = { product ->
                    onProductClick(product)
                }
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(top = 34.dp, bottom = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Popular",
                    color = DarkText,
                    fontSize = 12.sp
                )

                Text(
                    text = "See All",
                    color = Accent,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        items(productList.reversed()) { product ->
            ProductSmallCard(
                modifier = Modifier
                    .padding(horizontal = 22.dp)
                    .padding(bottom = 16.dp),
                product = product,
                onProductClick = { product ->
                    onProductClick(product)
                },
                onAddToCartClick = {

                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    ShoesAppTheme {
        HomeContent(onProductClick = {})
    }
}