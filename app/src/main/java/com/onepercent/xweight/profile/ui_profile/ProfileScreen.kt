package com.onepercent.xweight.profile.ui_profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.onepercent.xweight.profile.ui_profile.components.GeneralInformation

@Composable
fun ProfileScreen() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Profile") },
                backgroundColor = Color.White,
                actions = { }
            )
        },
        content = {

//            Text(
//                modifier = Modifier.fillMaxWidth(),
//                text = "General"
//            )

            //GeneralInformation()
            
            Surface(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 10.dp),
                elevation = 3.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { }
                ) {
                    Text(text = "Delete all data")
                }
            }

        }
    )
}