
package com.example.tugasprak4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.TextButton
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tugasprak4.ui.theme.TugasPrak4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasPrak4Theme {
                BiodataScreen()
            }
        }
    }
}

@Composable
fun BiodataScreen() {

    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var tanggalLahir by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    var experience by remember { mutableStateOf("") }
    var expandedExperience by remember { mutableStateOf(false) }

    var android by remember { mutableStateOf(false) }
    var uiux by remember { mutableStateOf(false) }
    var web by remember { mutableStateOf(false) }
    var tampilkanHasil by remember { mutableStateOf(false) }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = colorResource(R.color.white)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(R.drawable.gambar_pink),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(dimensionResource(R.dimen.header_height)),
                contentScale = androidx.compose.ui.layout.ContentScale.Crop
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.78f)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(
                    topStart = dimensionResource(R.dimen.card_corner),
                    topEnd = dimensionResource(R.dimen.card_corner)
                ),
                colors = CardDefaults.cardColors(
                    containerColor = colorResource(R.color.white)
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = dimensionResource(R.dimen.card_elevation)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(dimensionResource(R.dimen.form_padding)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.form_title),
                        color = colorResource(R.color.purple_primary),
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(
                            dimensionResource(R.dimen.field_spacing)
                        )
                    )

                    Text(
                        text = stringResource(R.string.form_subtitle),
                        color = colorResource(R.color.text_secondary)
                    )


                    Spacer(Modifier.height(28.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            modifier = Modifier.weight(1f),
                            label = { Text(stringResource(R.string.first_name)) },
                            singleLine = true
                        )

                        OutlinedTextField(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            modifier = Modifier.weight(1f),
                            label = { Text(stringResource(R.string.last_name)) },
                            singleLine = true
                        )
                    }

                    Spacer(Modifier.height(12.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(stringResource(R.string.email)) },
                        singleLine = true
                    )

                    Spacer(Modifier.height(12.dp))


                    Spacer(Modifier.height(12.dp))

// Tanggal Lahir dan Experience
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedTextField(
                            value = tanggalLahir,
                            onValueChange = {},
                            modifier = Modifier.weight(1f),
                            label = { Text(stringResource(R.string.birth_date)) },
                            readOnly = true,
                            trailingIcon = {
                                TextButton(onClick = { showDatePicker = true }) {
                                    Text("▦")
                                }
                            },
                            singleLine = true
                        )

                        Box(modifier = Modifier.weight(1f)) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = {},
                                modifier = Modifier.fillMaxWidth(),
                                label = { Text(stringResource(R.string.experience)) },
                                readOnly = true,
                                trailingIcon = {
                                    TextButton(onClick = {
                                        expandedExperience = true
                                    }) {
                                        Text("▾")
                                    }
                                },
                                singleLine = true
                            )

                            DropdownMenu(
                                expanded = expandedExperience,
                                onDismissRequest = { expandedExperience = false }
                            ) {
                                listOf("Beginner", "Intermediate", "Experienced")
                                    .forEach { option ->
                                        DropdownMenuItem(
                                            text = { Text(option) },
                                            onClick = {
                                                experience = option
                                                expandedExperience = false
                                            }
                                        )
                                    }
                            }
                        }
                    }

// Dialog kalender
                    if (showDatePicker) {
                        val datePickerState = rememberDatePickerState(
                            yearRange = 1950..2026
                        )

                        DatePickerDialog(
                            onDismissRequest = { showDatePicker = false },
                            confirmButton = {
                                TextButton(onClick = {
                                    datePickerState.selectedDateMillis?.let { millis ->
                                        tanggalLahir = SimpleDateFormat(
                                            "dd/MM/yyyy",
                                            Locale.getDefault()
                                        ).format(Date(millis))
                                    }
                                    showDatePicker = false
                                }) {
                                    Text("OK")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDatePicker = false }) {
                                    Text("Batal")
                                }
                            }
                        ) {
                            DatePicker(state = datePickerState)
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.choose_skill),
                        modifier = Modifier.fillMaxWidth(),
                        fontWeight = FontWeight.Bold
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = android,
                            onCheckedChange = { android = it }
                        )
                        Text(stringResource(R.string.android_skill))

                        Checkbox(
                            checked = uiux,
                            onCheckedChange = { uiux = it }
                        )
                        Text(stringResource(R.string.uiux_skill))

                        Checkbox(
                            checked = web,
                            onCheckedChange = { web = it }
                        )
                        Text(stringResource(R.string.web_skill))
                    }

                    Spacer(Modifier.height(20.dp))


                    Button(
                        onClick = {
                            tampilkanHasil = true
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = true,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.purple_primary),
                            contentColor = colorResource(R.color.white)
                        )
                    ) {
                        Text(stringResource(R.string.proceed))
                    }

                    if (tampilkanHasil) {
                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Hasil Biodata",
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.purple_primary)
                        )

                        Text("Nama depan: $namaDepan")
                        Text("Nama belakang: $namaBelakang")
                        Text("Email: $email")
                        Text("Tgl Lahir: $tanggalLahir")
                        Text("Experience: $experience")

                        Text(
                            "Skill: " + listOfNotNull(
                                if (android) "Android" else null,
                                if (uiux) "UI/UX" else null,
                                if (web) "Web" else null
                            ).joinToString(", ")
                        )
                    }




                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BiodataPreview() {
    TugasPrak4Theme {
        BiodataScreen()
    }
}
