package com.example.t1g1_estebanblanco.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.t1g1_estebanblanco.R
import com.example.t1g1_estebanblanco.navigation.AppScreens

@Composable
fun HomeScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .padding(100.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(AppScreens.GuessGame.name)
                },

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Image(
                painter = painterResource(id = R.drawable.numbers),
                contentDescription = "Pantalla de adivinar numeros",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "¡Adivina el número!", fontSize = 20.sp,
                modifier = Modifier.weight(0.2f)
            )
        }


        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .clickable {
                    navController.navigate("F1")
                },

            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

            Image(
                painter = painterResource(id = R.drawable.driver),
                contentDescription = "Pantalla pilotos",
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.8f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pilotos F1", fontSize = 20.sp,
                modifier = Modifier.weight(0.2f)

            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    val nc = rememberNavController()
    HomeScreen(nc)
}