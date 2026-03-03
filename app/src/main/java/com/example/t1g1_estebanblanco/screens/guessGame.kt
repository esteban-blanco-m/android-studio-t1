package com.example.t1g1_estebanblanco.screens

import android.util.Log

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.t1g1_estebanblanco.R
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlin.random.Random
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.ui.text.style.TextAlign


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GuessGame(navController: NavController) {

    var counter by remember { mutableStateOf(0) }
    var field by remember { mutableStateOf("") }

    var randomNumber by remember {
        val number = Random.nextInt(1, 100)
        Log.i("GuessGame", "Number is $number")
        mutableStateOf(number)
    }
    var message by remember { mutableStateOf("Adivina el número") }
    var finished by remember { mutableStateOf(false) }

    Scaffold(topBar = {
        TopAppBar(
            title = {
            Column {
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = "Adivina El Número", fontSize = 25.sp, fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text(
                    text = "Intenta adivinar el número aleatorio",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Light
                )
            }

        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Volver a home"
                )
            }
        },

            colors = TopAppBarColors(
                containerColor = colorResource(id = R.color.aquamarine),
                scrolledContainerColor = Color.White,
                navigationIconContentColor = Color.White,
                titleContentColor = Color.White,
                actionIconContentColor = Color.White

            )
        )
    }, floatingActionButton = {
        FloatingActionButton(
            onClick = {
                counter = 0
                field = ""
                message = "Adivina el número"
                randomNumber = Random.nextInt(1, 100)
                finished = false

                Log.i("GuessGame", "Nuevo número: $randomNumber")
            }) {
            Icon(
                imageVector = Icons.Default.Refresh, contentDescription = "Reiniciar"
            )
        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(
                15.dp, alignment = Alignment.CenterVertically
            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Ingresa un número del 1 al 100",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                textAlign = TextAlign.Center
            )
            TextField(value = field, onValueChange = {
                field = it
            }, enabled = !finished, modifier = Modifier.fillMaxWidth(), placeholder = {
                Text("Ingresa número")
            })
            Button(
                onClick = {
                    counter++
                    val guess = field.toIntOrNull()
                    if (guess != null) {
                        if (guess == randomNumber) {
                            message = "Ganaste ya que el número era $randomNumber"
                            finished = true
                        } else if (guess < randomNumber) {
                            message = "El número es más grande"
                        } else {
                            message = "El número es más pequeño"
                        }
                    }
                }, enabled = !finished, colors = buttonColors(
                    containerColor = colorResource(id = R.color.aquamarine),
                    contentColor = Color.White
                )
            ) {
                Text("Jugar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Intentos: ", fontWeight = FontWeight.Bold, fontSize = 20.sp
                )
                Text(
                    text = "$counter", fontSize = 20.sp
                )
            }
            Text(
                message, fontSize = 20.sp, color = if (finished) Color.Green else Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuessGamePreview() {
    val nc = rememberNavController()
    GuessGame(nc)
}