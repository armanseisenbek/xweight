package com.onepercent.xweight.ui.main.components.content.weight_goal

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onepercent.weight_domain.WeightMeasurement
import com.onepercent.xweight.ui.components.CircularIndeterminateProgressBar

import java.text.SimpleDateFormat
import java.util.*

@Composable
fun WeightGoal(
    startWeight: WeightMeasurement,
    currentWeight: Double,
    goalWeight: Double
) {
//    var progress by remember { mutableStateOf(0.0) }
//
//    val animatedProgress = animateFloatAsState(
//        targetValue = progress.toFloat(),
//        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
//    ).value

    Column(modifier = Modifier
        .padding(10.dp)
        .border(1.dp, Color(0xFFeeeeee), RoundedCornerShape(5.dp))
        //.border(1.dp, Color.Blue)
        .fillMaxWidth()
        .clickable {  }
        .padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        //.padding(10.dp)
    ) {

        WeightGoalProgress()

    }
}