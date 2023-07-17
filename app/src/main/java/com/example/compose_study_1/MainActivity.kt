package com.example.compose_study_1

import android.app.Activity
import android.app.admin.DevicePolicyManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.UserManager
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.addCallback
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_study_1.ui.calculator.CalculatorViewModel
import com.example.compose_study_1.ui.main.Greeting
import com.example.compose_study_1.ui.main.MainScreen
import com.example.compose_study_1.ui.theme.Composestudy1Theme
import com.example.compose_study_1.ui.theme.MediumGray
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {

    private val TAG = this.javaClass.simpleName

    private val KIOSK_PACKAGE = "com.example.compose_study_1"
    private val APP_PACKAGES = arrayOf(KIOSK_PACKAGE)

    private val dpm by lazy(LazyThreadSafetyMode.NONE) { getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager }
    private lateinit var componentName: ComponentName

    private var isTaskMode = false


    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Keep the screen on and bright while this kiosk activity is running.
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        componentName = ComponentName(this, DeviceAdminSimple::class.java)

        setContent {
            Composestudy1Theme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val buttonSpacing = 8.dp

                val view = LocalView.current

                // SystemUiController
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = !isSystemInDarkTheme()

                LaunchedEffect(systemUiController) {
                    Log.e(TAG, "LaunchedEffect(systemUiController)")
                }

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

//                Calculator(
//                    state = state,
//                    onAction = viewModel::onAction,
//                    buttonSpacing = buttonSpacing,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .background(MediumGray)
//                        .padding(16.dp)
//                )

                MainScreen(appbarName = "Main AppBar") { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
//                        Greeting(name = "Hello World!!")
//                        Greeting(name = "Test 1")
//                        Greeting(name = "Test 2")
//                        Greeting(name = "Test test test test test test 3")
                        Button(
                            onClick = {
                                profileOwn()
                            }
                        ) {
                            Text(text = "Profile Own permission enable")
                        }
                        Button(
                            onClick = {
//                                enableAdmin()
                                if (isDeviceAdminApp()) {
                                    Log.e(TAG, "ljhtest - admin enable")
                                    enableAdmin()
                                } else {
                                    Log.e(TAG, "ljhtest - admin disable")
                                    disableAdmin()
                                }
                            }
                        ) {
                            Text(text = "admin permission enable/disable")
                        }

                        Button(
                            onClick = {
                                startLockTask()
                                WindowCompat.setDecorFitsSystemWindows(window, false)
                                WindowInsetsControllerCompat(window, view).apply {
                                    hide(WindowInsetsCompat.Type.systemBars())
                                    systemBarsBehavior =
                                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                                }
                            }
                        ) {
                            Text(text = "kiosk mode enable")
                        }

                        Button(
                            onClick = {
                                stopLockTask()
                                WindowCompat.setDecorFitsSystemWindows(window, false)
                                WindowInsetsControllerCompat(window, view).apply {
                                    show(WindowInsetsCompat.Type.systemBars())
                                    systemBarsBehavior =
                                        WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                                }
                            }
                        ) {
                            Text(text = "kiosk mode disable")
                        }

                        AnimatedVisibility(visible = false) {
                            Button(
                                onClick = {
                                    // disable camera
                                    val c = dpm.getCameraDisabled(componentName)
                                    dpm.setCameraDisabled(componentName, !c)
                                }
                            ) {
                                Text(text = "Camera disable")
                            }
                        }

                        AnimatedVisibility(visible = false) {
                            Button(
                                onClick = {
                                    // disable camera
                                    val b = dpm.getBluetoothContactSharingDisabled(componentName)
                                    dpm.setBluetoothContactSharingDisabled(componentName, !b)
                                }
                            ) {
                                Text(text = "Bluetooth disable")
                            }
                        }

                    }
                }
            }
        }


//        if (!isDeviceAdminApp()) {
//            enableAdmin()
//        }

        onBackPressedDispatcher.addCallback(owner = this, onBackPressed = {
            Log.e(TAG, "onCreate: ljhtest - onBackPressed()")
            return@addCallback
        })
    }


    private fun isDeviceAdminApp(): Boolean {
        return dpm.isAdminActive(componentName)
    }

    private fun profileOwn() {
        val intent = Intent(DevicePolicyManager.ACTION_PROVISION_MANAGED_PROFILE).apply {
            putExtra(DevicePolicyManager.EXTRA_PROVISIONING_DEVICE_ADMIN_COMPONENT_NAME, componentName)
        }
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    private fun enableAdmin() {
//        if (isDeviceAdminApp()) return

        val intent = Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN)
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName)
        startActivityForResult(intent, Activity.RESULT_OK)
    }

    private fun disableAdmin() {
//        if (!isDeviceAdminApp()) return
        dpm.removeActiveAdmin(componentName)
    }


    override fun onStart() {
        super.onStart()
        /*        // Set an option to turn on lock task mode when starting the activity.
                val options = ActivityOptions.makeBasic()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    options.setLockTaskEnabled(true)
                }


                // Start our kiosk app's main activity with our lock task mode option.
                val launchIntent = packageManager.getLaunchIntentForPackage(KIOSK_PACKAGE)
                if (launchIntent != null) {
                    startActivity(launchIntent, options.toBundle())
                }*/
    }

    override fun onResume() {
        super.onResume()
        /*        // First, confirm that this package is allowlisted to run in lock task mode.
                if (dpm.isLockTaskPermitted(packageName)) {
                    startLockTask()
                } else {
                    // Because the package isn't allowlisted, calling startLockTask() here
                    // would put the activity into screen pinning mode.
                }*/
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

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Log.e(TAG, "onTouchEvent: ljhtest ${event?.action}")
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.e(TAG, "ljhtest - down(${event.x}, ${event.y})")
            }

            MotionEvent.ACTION_MOVE -> {
                Log.w(TAG, "ljhtest - move(${event.x}, ${event.y})")
            }

            MotionEvent.ACTION_UP -> {
                Log.v(TAG, "ljhtest - up(${event.x}, ${event.y})")
            }
        }

        return super.onTouchEvent(event)
    }

    private fun actionLockTaskMode(view: View, isTaskMode: Boolean) {
        WindowCompat.setDecorFitsSystemWindows(window, !isTaskMode)
        val windowController = WindowInsetsControllerCompat(window, view)
        if (isTaskMode) {
//            startLockTask()
            windowController.hide(WindowInsetsCompat.Type.systemBars())
        } else {
//            stopLockTask()
            windowController.show(WindowInsetsCompat.Type.systemBars())
        }

//        if (dpm.isLockTaskPermitted(packageName)) {
//            val options = ActivityOptions.makeBasic()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                options.setLockTaskEnabled(true)
//            }
//            // Start our kiosk app's main activity with our lock task mode option.
//            val launchIntent = packageManager.getLaunchIntentForPackage(KIOSK_PACKAGE)
//            if (launchIntent != null) {
//                startActivity(launchIntent, options.toBundle())
//            }
//        } else {
//            dpm.setLockTaskPackages(componentName, APP_PACKAGES)
//        }
    }
}
