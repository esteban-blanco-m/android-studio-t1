package com.example.t1g1_estebanblanco.screens

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.TagFaces
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.t1g1_estebanblanco.R
import com.example.t1g1_estebanblanco.model.Driver

fun loadDrivers(
    context: Context, onResult: (MutableList<Driver>) -> Unit
) {
    val drivers = mutableListOf<Driver>()
    val url = "https://api.openf1.org/v1/drivers"
    val queue = Volley.newRequestQueue(context)
    val request = JsonArrayRequest(Request.Method.GET, url, null, { response ->
        for (i in 0 until response.length()) {
            val jsonObject = response.getJSONObject(i)
            val number: String = jsonObject.getString("driver_number")
            val name = jsonObject.getString("full_name")
            val acronym = jsonObject.getString("name_acronym")
            val team = jsonObject.getString("team_name")
            val image = jsonObject.getString("headshot_url")
            val teamColor = jsonObject.optString("team_colour", "000000")
            val driver = Driver(
                number, name, acronym, team, image, teamColor
            )
            drivers.add(driver)
        }
        onResult(drivers)
    }, {
        it.printStackTrace()
    })
    queue.add(request)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Drivers(
    controller: NavController
) {
    val context = LocalContext.current
    var drivers by remember {
        mutableStateOf(
            mutableListOf<Driver>()
        )
    }

    // evitamos que la api se llame inf veces
    // cargar solo cuando se abara
    LaunchedEffect(Unit) {
        loadDrivers(context) {
            drivers = it
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                Text(
                    text = "F1 Drivers 2025",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    controller.popBackStack()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Volver",
                        tint = Color.White
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = colorResource(id = R.color.aquamarine),
            )
            )
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(drivers) { driver ->

                ElevatedCard(
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = colorResource(id = R.color.morao)
                    ), modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                            controller.navigate(
                                "driversDetail/" + "${driver.number}/" + "${Uri.encode(driver.name)}/" + "${
                                    Uri.encode(
                                        driver.acronym
                                    )
                                }/" + "${Uri.encode(driver.team)}/" + "${Uri.encode(driver.image)}" + "/${
                                    Uri.encode(
                                        driver.teamColor
                                    )
                                }"

                            )

                        }) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Icon(
                            Icons.Default.TagFaces, contentDescription = "piloto"
                        )
                        Text(
                            text = driver.number, fontSize = 16.sp
                        )
                        Text(
                            text = driver.name, fontSize = 16.sp
                        )

                    }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DriversPreview() {
    val nc = rememberNavController()
    Drivers(nc)
}