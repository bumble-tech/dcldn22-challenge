package com.bumble.challenge1.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumble.challenge1.ui.theme.appyx_dark
import com.bumble.challenge1.ui.theme.appyx_yellow1

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Button(
        modifier = modifier.padding(horizontal = 8.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = appyx_yellow1,
            contentColor = appyx_dark
        )
    ) { content() }
}
