package com.example.productivity

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

@Composable
fun RoundedCornerIndicatorBg(
    /*@FloatRange(from = 0.0, to = 1.0)*/
    progress: Float,
    modifier: Modifier = Modifier,
    foregroundColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.primary.copy
        (alpha = .3f),
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth
){
    Box(){
        RoundedCornerIndicator(progress = progress,
            color=foregroundColor, strokeWidth = strokeWidth,
            modifier = modifier.fillMaxSize())
        RoundedCornerIndicator(progress = 1f,color=backgroundColor,
            strokeWidth = strokeWidth,modifier = modifier
                .fillMaxSize())
    }
}