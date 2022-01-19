package com.onepercent.xweight.ui.main.components.content.bmi

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BmiBlock() {

    Column {

        BmiInfoRow()

        BmiLine(
            modifier = Modifier
                .height(40.dp)
                //.border(1.dp, Color.Cyan)
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp, start = 15.dp, end = 15.dp)
        )
    }
}