package com.onepercent.xweight.profile.ui_profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GeneralInformation(

) {

    Surface(
        modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
        elevation = 3.dp
    ) {

        Column {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clickable { }
                ) {
                    Text(
                        //modifier = ,
                        text = "Gender",
                        fontSize = 15.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Male, contentDescription = "Male Icon")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Female, contentDescription = "Female Icon")
                        }
                    }
                }

                Divider(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp))

                Column(modifier = Modifier
                    .weight(1f)
                    .clickable { }) {
                    Text(
                        //modifier = ,
                        text = "Gender",
                        fontSize = 15.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Male, contentDescription = "Male Icon")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Female, contentDescription = "Female Icon")
                        }
                    }
                }
            }

            Divider()

            Row(modifier = Modifier.height(IntrinsicSize.Min).fillMaxWidth()) {
                Column(modifier = Modifier
                    .weight(1f)
                    .clickable { }) {
                    Text(
                        //modifier = ,
                        text = "Gender",
                        fontSize = 15.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Male, contentDescription = "Male Icon")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Female, contentDescription = "Female Icon")
                        }
                    }
                }

                Divider(modifier = Modifier
                    .fillMaxHeight()
                    .width(1.dp))

                Column(modifier = Modifier
                    .weight(1f)
                    .clickable { }) {
                    Text(
                        //modifier = ,
                        text = "Gender",
                        fontSize = 15.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {

                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Male, contentDescription = "Male Icon")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.Female, contentDescription = "Female Icon")
                        }
                    }
                }
            }
        }





    }
}