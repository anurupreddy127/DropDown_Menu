package com.anurup.navigationdrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.anurup.navigationdrawer.ui.theme.NavigationDrawerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DropDownMenu()
        }
    }
}


@Composable
fun DropDownMenu(){

    var expanded by remember { mutableStateOf(false) }
    val list = listOf("About Us","Administration","Academics","Admissions")
    var selectedItems by remember { mutableStateOf("") }

    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.ArrowDropDown
    }


    Column(modifier = Modifier.padding(20.dp)) {

        OutlinedTextField(
            value = selectedItems,
            onValueChange = { selectedItems = it},
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.toSize()
                },
            label = { Text(text = "Selected Items")},
            trailingIcon = {
                Icon(icon, contentDescription = "",Modifier.clickable { expanded = !expanded })

            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current){textFieldSize.width.toDp()})
        ) {

            list.forEach{ label ->
                DropdownMenuItem(onClick = {
                    selectedItems = label
                    expanded = false
                }) {
                    Text(text = label)
                }
                
            }

        }

    }

}