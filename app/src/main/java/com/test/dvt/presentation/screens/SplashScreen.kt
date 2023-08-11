package com.test.dvt.presentation.screens

import android.Manifest
import android.content.Context
import android.content.Intent
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.test.dvt.R
import com.test.dvt.presentation.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@OptIn(ExperimentalPermissionsApi::class)
@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun SplashScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val permissions = listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    )
    val requiredPermissions: List<String> = listOf(permissions.first())
    var errorText by remember {
        mutableStateOf("")
    }

    val permissionState = rememberMultiplePermissionsState(permissions = permissions) { map ->
        val rejectedPermissions = map.filterValues { !it }.keys
        errorText = if (rejectedPermissions.none { it in requiredPermissions }) {
            ""
        } else {
            "${rejectedPermissions.joinToString()} required for the sample"
        }
    }
    val allRequiredPermissionsGranted =
        permissionState.revokedPermissions.none { it.permission in requiredPermissions }

    var showPermissionDialog by remember(permissionState) {
        mutableStateOf(false)
    }

    var startMainActivity by remember(permissionState) {
        mutableStateOf(false)
    }

    var showRationale by remember(permissionState) {
        mutableStateOf(false)
    }

    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var lonInfo by remember {
        mutableDoubleStateOf(0.0)
    }
    var latInfo by remember {
        mutableDoubleStateOf(0.0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.weather_animation))
        val logoAnimationState = animateLottieCompositionAsState(composition = composition)
        LottieAnimation(
            composition = composition,
            progress = { logoAnimationState.progress },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(300.dp),
            contentScale = ContentScale.Fit,
        )
        Text(
            text = stringResource(R.string.weather_forecasts),
            style = TextStyle(
                fontSize = 50.sp,
                lineHeight = 50.12.sp,
                fontWeight = FontWeight(700),
                color = Color.Black,
                textAlign = TextAlign.Center,
            )
        )

        Button(
            onClick = {
                if (allRequiredPermissionsGranted) {
                    permissionState.permissions.filter {
                        it.status.isGranted
                    }.map { it.permission }
                    startMainActivity = allRequiredPermissionsGranted
                } else {
                    showPermissionDialog = true
                }
            },
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Red,
                containerColor = Color(0xFFDDB130)
            ),
            modifier = Modifier
                .width(300.dp)
                .height(50.dp)
        ) {
            Text(
                text = stringResource(R.string.get_start),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF362A84),
                    textAlign = TextAlign.Center,
                )
            )
        }

        if (showPermissionDialog) {
            AlertDialog(
                onDismissRequest = {
                    showPermissionDialog = false
                },
                title = {
                    Text(text = stringResource(R.string.permissions_request_title))
                },
                text = {
                    Text(text = stringResource(R.string.permission_request_desc))
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showPermissionDialog = false
                            if (permissionState.shouldShowRationale) {
                                showRationale = true
                            } else {
                                permissionState.launchMultiplePermissionRequest()
                            }
                        },
                    ) {
                        Text(stringResource(R.string.grant))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showPermissionDialog = false
                        },
                    ) {
                        Text(stringResource(R.string.dismiss))
                    }
                },
            )
        }

        if (showRationale) {
            AlertDialog(
                onDismissRequest = {
                    showRationale = false
                },
                title = {
                    Text(text = stringResource(R.string.required_permissions))
                },
                text = {
                    Text(text = "The following permissions are required\n $permissions\n for a detailed and informative experience")
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showRationale = false
                            permissionState.launchMultiplePermissionRequest()
                        },
                    ) {
                        Text(stringResource(R.string.proceed))
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showRationale = false
                        },
                    ) {
                        Text(stringResource(id = R.string.dismiss))
                    }
                },
            )
        }

        if (startMainActivity) {
            LaunchedEffect(key1 = true) {
                scope.launch(Dispatchers.IO) {
                    val result = locationClient.lastLocation.await()
                    if (result == null) {
                        val priority =
                            if (permissions.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
                                Priority.PRIORITY_HIGH_ACCURACY
                            } else {
                                Priority.PRIORITY_BALANCED_POWER_ACCURACY
                            }
                        val locationRes = locationClient.getCurrentLocation(
                            priority,
                            CancellationTokenSource().token,
                        ).await()
                        locationRes?.let { fetchedLocation ->
                            latInfo = fetchedLocation.latitude
                            lonInfo = fetchedLocation.longitude
                        }
                        startMainActivity(context, lon = lonInfo, lat = latInfo)
                    } else {
                        latInfo = result.latitude
                        lonInfo = result.longitude
                        startMainActivity(context, lon = lonInfo, lat = latInfo)
                    }
                }
            }
        }
    }
}

fun startMainActivity(context: Context, lon: Double, lat: Double) {
    val intent = Intent(context, MainActivity::class.java)
    intent.putExtra("lon", lon)
    intent.putExtra("lat", lat)
    startActivity(context, intent, null)
}
