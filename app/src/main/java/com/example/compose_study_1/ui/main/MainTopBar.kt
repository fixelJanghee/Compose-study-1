package com.example.compose_study_1.ui.main

import android.content.ContentValues
import android.util.Log
import android.widget.Toast
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.compose_study_1.R
import com.example.compose_study_1.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(name: String) {
    val context = LocalContext.current
    var expanded: Boolean by remember { mutableStateOf(false) }
    CenterAlignedTopAppBar(
        title = {
            Text(text = name)
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    Toast.makeText(context, "navigationIcon click !", Toast.LENGTH_SHORT).show()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_expand_24),
                    contentDescription = null
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    expanded = true
                    Log.e(ContentValues.TAG, "Notification Dropdown show !")
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_notifications_24),
                    contentDescription = null
                )
            }

            NotificationMenu(expanded = expanded) {
                expanded = false
                Log.e(ContentValues.TAG, "Notification Dropdown show !")
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Purple40
        )
    )
}