package com.example.t1g1_estebanblanco.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.t1g1_estebanblanco.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversDetail(
    number: String?,
    name: String?,
    acronym: String?,
    team: String?,
    imageUrl: String?,
    teamColor: String?,
    controller: NavController
) {
    //declarar un color por defecto
    var colorTeamC = Color.Black

    if (teamColor != null && teamColor.isNotEmpty()) {
        colorTeamC = Color(android.graphics.Color.parseColor("#$teamColor"))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detalle del Piloto",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }, navigationIcon = {

                    IconButton(onClick = {
                        controller.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = "Volver"
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.aquamarine),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = imageUrl,
                contentDescription = "Foto de $name",
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .size(150.dp)
            )

            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Text(text = "Number: ", fontSize = 18.sp)
                Text(text = number ?: "", fontSize = 18.sp)
            }

            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Text(text = "Name: ", fontSize = 18.sp)
                Text(text = name ?: "", fontSize = 18.sp)
            }

            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Text(text = "Acronym: ", fontSize = 18.sp)
                Text(text = acronym ?: "", fontSize = 18.sp)
            }

            Row(modifier = Modifier.padding(vertical = 6.dp)) {
                Text(text = "Team: ", fontSize = 18.sp, color = colorTeamC)
                Text(text = team ?: "", fontSize = 18.sp, color = colorTeamC)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DriversDetailPreview() {
    val nc = rememberNavController()
    DriversDetail(
        number = "16",
        name = "Charles LECLERC",
        acronym = "LEC",
        team = "Ferrari",
        imageUrl = "https://media.formula1.com/d_driver_fallback_image.png/content/dam/fom-website/drivers/C/CHALEC01_Charles_Leclerc/chalec01.png.transform/2col/image.png",
        teamColor = "FF2800",
        controller = nc
    )
}