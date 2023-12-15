package com.example.unitconverter

import android.os.Bundle
import android.webkit.WebSettings.TextSize
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme

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
                    UnitCoverter()
                }
            }
        }
    }
}

@Composable
fun UnitCoverter(){
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally


    ){
        // All the UI element will be placed vertically
        Text("Unit Converter")
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = "", onValueChange = {
            // What should happen when value is changed
        })

        Spacer(modifier = Modifier.height(16.dp))


        Row {
            // All the UI element will be placed horizontally

            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Sown")

                }
                DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = { /*TODO*/ })
                }
            }
            Spacer(modifier = Modifier.width(26.dp))

            Box {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Select")
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "Arrow Down" )

                }
                DropdownMenu(expanded = false, onDismissRequest = { /*TODO*/ }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = { /*TODO*/ })
                    DropdownMenuItem(text = { Text(text = "Millimeters") }, onClick = { /*TODO*/ })
                }
            }


        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result:")
    }
}




@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitCoverter()
}