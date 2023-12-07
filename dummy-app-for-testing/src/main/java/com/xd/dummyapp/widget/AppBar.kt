package com.xd.dummyapp.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.xd.dummyapp.LocalNavController
import com.xd.dummyapp.R

@Composable
fun AppBar(titleId: Int = 0, titleText: String = "", showBackButton: Boolean = true) {
    val navController = LocalNavController.current
    TopAppBar(
        title = { Text(text = if (titleId != 0) stringResource(titleId) else titleText) },
        navigationIcon = if (showBackButton) {
            {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(
                            R.string.navigate_up
                        )
                    )
                }
            }
        } else null
    )
}