package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val initialProfile by viewModel.restaurantProfile.collectAsState()
    val scrollState = rememberScrollState()

    // Form field states
    var name by remember { mutableStateOf(initialProfile.name) }
    var address by remember { mutableStateOf(initialProfile.address) }
    var description by remember { mutableStateOf(initialProfile.description) }
    var openingHours by remember { mutableStateOf(initialProfile.openingHours) }

    // Validation
    val isFormValid = remember(name, address, description, openingHours) {
        name.isNotBlank() && address.isNotBlank() && description.isNotBlank() && openingHours.isNotBlank()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Edit Profil Restoran",
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier.testTag("edit_profile_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Kembali ke Profil"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(scrollState)
                .padding(16.dp)
        ) {
            Text(
                text = "Kelola Informasi Restoran",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Form Fields
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nama Restoran") },
                placeholder = { Text("Contoh: Bistro Rasa Nusantara") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Store, contentDescription = "Nama Restoran")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("edit_input_name"),
                singleLine = true,
                isError = name.isBlank(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Alamat Restoran") },
                placeholder = { Text("Contoh: Jl. Cihampelas No. 45, Bandung") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "Alamat")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("edit_input_address"),
                isError = address.isBlank(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = openingHours,
                onValueChange = { openingHours = it },
                label = { Text("Jam Operasional") },
                placeholder = { Text("Contoh: 10:00 - 22:00 WIB") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccessTime, contentDescription = "Jam Operasional")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("edit_input_hours"),
                singleLine = true,
                isError = openingHours.isBlank(),
                shape = RoundedCornerShape(12.dp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Deskripsi Singkat") },
                placeholder = { Text("Jelaskan keistimewaan kuliner restoran Anda...") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Description, contentDescription = "Deskripsi")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .padding(bottom = 24.dp)
                    .testTag("edit_input_desc"),
                isError = description.isBlank(),
                shape = RoundedCornerShape(12.dp)
            )

            // Submit Actions Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Button Batal (Cancel)
                OutlinedButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp)
                        .testTag("edit_cancel_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy() // Custom borders preserved
                ) {
                    Icon(imageVector = Icons.Default.Cancel, contentDescription = "Batal")
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(text = "Batal", fontWeight = FontWeight.SemiBold, fontSize = 15.sp)
                }

                // Button Simpan (Save)
                Button(
                    onClick = {
                        if (isFormValid) {
                            viewModel.updateProfile(name, address, description, openingHours)
                            onNavigateBack() // Popping back
                        }
                    },
                    modifier = Modifier
                        .weight(1.5f)
                        .height(54.dp)
                        .testTag("edit_save_button"),
                    shape = RoundedCornerShape(12.dp),
                    enabled = isFormValid,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Simpan")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Simpan", fontWeight = FontWeight.Bold, fontSize = 15.sp)
                }
            }
        }
    }
}
