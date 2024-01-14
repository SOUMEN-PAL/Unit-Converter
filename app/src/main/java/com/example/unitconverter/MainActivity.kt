package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){
// states //
    var inputValue = remember { mutableStateOf("") }
    var outputValue = remember { mutableStateOf("0")}

    var inputUnit = remember { mutableStateOf("From") }
    var outputUnit = remember { mutableStateOf("To") }
    var resultUnit = remember { mutableStateOf("") }
    var iExpanded = remember { mutableStateOf(false) }
    var oExpanded = remember { mutableStateOf(false) }

    val iConversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    // functions //
    val context = LocalContext.current
    fun convert(){
        // Elvis Operator
        val inputValueDouble = inputValue.value.toDoubleOrNull()
        if(inputValueDouble == null){
            outputValue.value = "0"
            Toast.makeText(context, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            resultUnit.value = ""

            return
        }
        val result = inputValueDouble * (iConversionFactor.doubleValue / oConversionFactor.doubleValue)
        outputValue.value = result.toString()
        resultUnit.value = outputUnit.value

    }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))

        // Input Box //
        OutlinedTextField(
            value = inputValue.value , onValueChange = {
                inputValue.value = it
                inputUnit.value = "From"
                outputUnit.value = "To"
        },
            label = { Text(text = "Enter Value")}

        )


        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // Input BOx //
            Box{
                // A Box is a composable that allows you to stack composables on top of each other.
                // It is similar to a FrameLayout in Android View system.
                // similar to XML's layout_gravity

                // Input button //
                Button(onClick = { iExpanded.value = true }) {
                    Text(text = inputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }


                DropdownMenu(expanded = iExpanded.value, onDismissRequest = { iExpanded.value = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Centimeters"
                            iConversionFactor.doubleValue = 0.01

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Meters"
                            iConversionFactor.doubleValue = 1.0

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Feet"
                            iConversionFactor.doubleValue = 0.3048

                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            iExpanded.value = false
                            inputUnit.value = "Millimeters"
                            iConversionFactor.doubleValue = 0.001

                        }
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Output Box //
            Box{
                // val context = LocalContext.current

                // Output button //
                Button(onClick = { oExpanded.value = true }) {
                    Text(text = outputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }



                DropdownMenu(expanded = oExpanded.value, onDismissRequest = { oExpanded.value = false }) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpanded.value = false
                            outputUnit.value = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            oExpanded.value = false
                            outputUnit.value = "Meters"
                            oConversionFactor.doubleValue = 1.0
                            convert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpanded.value = false
                            outputUnit.value = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convert()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Millimeters") },
                        onClick = {
                            oExpanded.value = false
                            outputUnit.value = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convert()
                        }
                    )
                }
            }


        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Result: ${outputValue.value} ${resultUnit.value}")
    }

}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}