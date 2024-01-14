package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter(){
// states //
    val inputValue = remember { mutableStateOf("") }
    val outputValue = remember { mutableStateOf("0")}

    val inputUnit = remember { mutableStateOf("From") }
    val outputUnit = remember { mutableStateOf("To") }
    val resultUnit = remember { mutableStateOf("") }
    val iExpanded = remember { mutableStateOf(false) }
    val oExpanded = remember { mutableStateOf(false) }

    val iConversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }



    // Fonts //
    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold),
        Font(R.font.lexend_extralight, FontWeight.ExtraLight)
    )

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
        Text(
            text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp,
                    )
                ){
                    append("U")
                }
                append("nit ")
               withStyle(
                   style = SpanStyle(
                       color = Color.Green,
                       fontSize = 50.sp,
                   )
               ){
                   append("C")
               }
                append("onverter")

            },

            style = MaterialTheme.typography.headlineLarge,
            fontFamily = fontFamily,
            textAlign = TextAlign.Center


            )



        Spacer(modifier = Modifier.height(16.dp))

        // Input Box //
        OutlinedTextField(

            value = inputValue.value ,
            onValueChange = {
                inputValue.value = it
                inputUnit.value = "From"
                outputUnit.value = "To"
            },
            label = { Text(text = "Enter Value" , fontFamily = fontFamily , color = Color.Black) },
            shape = RoundedCornerShape(5.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                cursorColor = Color.Black,
                focusedBorderColor = Color.Green,
                unfocusedBorderColor = Color.Black,
                disabledBorderColor = Color.Black,
            )


        )


        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // Input BOx //
            Box{
                // A Box is a composable that allows you to stack composable on top of each other.
                // It is similar to a FrameLayout in Android View system.
                // similar to XAML's layout_gravity

                // Input button //
                Button(
                    onClick = { iExpanded.value = true },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    ),

                ) {
                    Text(text = inputUnit.value, fontFamily = fontFamily)
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
                Button(onClick = { oExpanded.value = true },
                    shape = RoundedCornerShape(5.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                        contentColor = Color.Black
                    )
                ) {
                    Text(text = outputUnit.value , fontFamily = fontFamily)
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

        // Convert Button //
        Text("Result: ${outputValue.value} ${resultUnit.value}",
            style = MaterialTheme.typography.headlineMedium,
        )
    }

}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}