package com.tech.peachmedia.feature_media_posts.presentation.theme

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text("Peach Media App") }
    )
}

@Preview
@Composable
fun TopBarPreview() {
    PeachAppTheme {
        TopBar()
    }
}
