package com.example.costofelectricity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.ui.Alignment
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.runtime.mutableFloatStateOf
import kotlin.math.roundToInt
import java.util.Locale
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.costofelectricity.ui.theme.CostOfElectricityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CostOfElectricityTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CostOfElectricity(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun CostOfElectricity(modifier: Modifier = Modifier) {

    var consumptionInput by remember { mutableStateOf("") }
    var price by remember { mutableFloatStateOf(0.25f) }
    var reducedVat by remember { mutableStateOf(false) }

    val roundedPrice = (price * 100).roundToInt() / 100f
    val formattedPrice = String.format(Locale.getDefault(), "%.2f", roundedPrice)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Cost of electricity"
        )

        OutlinedTextField(
            value = consumptionInput,
            onValueChange = { consumptionInput = it },
            label = { Text(text = "Consumption in kWh") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Text(
            text = "Price/kWh $formattedPrice"
        )

        Slider(
            value = price,
            onValueChange = { price = it },
            valueRange = 0f..1f,
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = reducedVat,
                onCheckedChange = { reducedVat = it }
            )

            Text(
                text = "VAT 10%"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CostOfElectricityPreview() {
    CostOfElectricityTheme {
        CostOfElectricity()
    }
}