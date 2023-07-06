package com.example.compose_study_1

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_study_1.ui.calculator.Calculator
import com.example.compose_study_1.ui.calculator.CalculatorViewModel
import com.example.compose_study_1.ui.main.Greeting
import com.example.compose_study_1.ui.main.MainScreen
import com.example.compose_study_1.ui.theme.Composestudy1Theme
import com.example.compose_study_1.ui.theme.MediumGray
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val TAG = this.javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContent {
            Composestudy1Theme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp

                // SystemUiController
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()


                DisposableEffect(systemUiController, useDarkIcons) {
                    systemUiController.setStatusBarColor(
                        color = MediumGray,
                        darkIcons = useDarkIcons
                    )
                    onDispose {
                        // key에 정의된 값이 변경되면 해당 Effect를 다시 설정한다.
                        // 다시 설정된 값은 onDispose() 에서 callback 받을 수 있음.
                        Log.e(TAG, "DisposableEffect(systemUiController)")
                    }
                }

                Calculator(
                    state = state,
                    onAction = viewModel::onAction,
                    buttonSpacing = buttonSpacing,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MediumGray)
                        .padding(16.dp)
                )
            }
        }
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
