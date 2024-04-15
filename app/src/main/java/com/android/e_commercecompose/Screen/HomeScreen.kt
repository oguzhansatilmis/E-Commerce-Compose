package com.android.e_commercecompose.Screen

import android.annotation.SuppressLint
import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.android.e_commercecompose.model.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import retrofit2.Response


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
 fun HomeScreen() {

    val viewModel: HomeViewModel = hiltViewModel()
    val state by viewModel.categoriesLiveData.observeAsState()

    println("state $state")

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        var loading by remember { mutableStateOf(false) }
        if (loading) {
            ProgressBar()
        }


        when (state) {

            is State.Success -> {
                loading = false
                println("Success")
                val categoryList = (state as State.Success<Response<List<String>>>).data
                categoryList?.let { it ->
                    it.body()?.forEach {
                        Text(text = it)
                    }
                }
            }

            is State.Loading -> {
                loading = true
            }

            is State.Error -> {
                println("Error")
            }

            else -> {
                println("else")
            }
        }

    }

}

@Composable
fun ProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = Color.Green)
    }
}


@Preview
@Composable
fun PreviewFun() {

    HomeScreen()
}