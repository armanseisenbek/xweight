package com.onepercent.xweight.ui.main.components.content.bmi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BmiInfoRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp)
            //.border(1.dp, Color.LightGray)
        ,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        content = {
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = "BMI".uppercase(),
                color = Color(0xFF494d50)
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp),
                text = "24.4 (Normal)",
                color = Color.Green
            )
        }
    )
}