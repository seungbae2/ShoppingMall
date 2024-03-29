package com.example.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.presentation.ui.theme.ShoppingMallTheme
import com.example.presentation.viewmodel.MainViewModel
import com.example.presentation.viewmodel.TempViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ShoppingMallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(googleSignInClient)
                }
            }
        }

        viewModel.updateColumnCount(getColumnCount())
    }

    private fun getColumnCount() : Int {
        return getDisplayWidthDp().toInt() / DEFAULT_COLUMN_SIZE
    }

    private fun getDisplayWidthDp() : Float {
        return resources.displayMetrics.run { widthPixels / density }
    }

    companion object {
        private const val DEFAULT_COLUMN_SIZE = 160
    }
}

