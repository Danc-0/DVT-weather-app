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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import com.test.dvt.R
import com.test.dvt.presentation.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@RequiresPermission(
    anyOf = [Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION],
)
@Composable
fun SplashScreen(usePreciseLocation: Boolean) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val locationClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    var lonInfo by remember {
        mutableStateOf(0.0)
    }
    var latInfo by remember {
        mutableStateOf(0.0)
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
            text = "Weather ForeCasts",
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
                scope.launch(Dispatchers.IO) {
                    val result = locationClient.lastLocation.await()
                    if (result == null) {
                        val priority = if (usePreciseLocation) {
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
                text = "Get Start",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF362A84),
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

fun startMainActivity(context: Context, lon: Double, lat: Double) {
    val intent = Intent(context, MainActivity::class.java)
    intent.putExtra("lon", lon)
    intent.putExtra("lat", lat)
    startActivity(context, intent, null)
}
