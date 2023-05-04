package com.example.composedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composedemo.ui.theme.ComposeDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {// 인터페이스 내용을 이 ㅎ안 함수에서 제공한다고 선언
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}
@Composable
fun DemoScreen(){
    var sliderPosition by remember {mutableStateOf(20f)}

    val handlePositionChange = {position: Float->
        sliderPosition =position
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ){
        DemoText(message = "Welcome to Compose", fontSize = sliderPosition)

        Spacer(modifier = Modifier.height(150.dp))

        DemoSlider(sliderPosition = sliderPosition, onPositionChange = handlePositionChange)

        Text(
            style = MaterialTheme.typography.h2,
            text = sliderPosition.toInt().toString() + "sp"
        )
    }
}

@Composable
fun DemoSlider(sliderPosition: Float, onPositionChange:(Float)->Unit){
    Slider(
        modifier = Modifier.padding(10.dp),
        valueRange = 20f..40f,
        value = sliderPosition,
        onValueChange = {onPositionChange(it)})

}

@Composable
fun DemoText(message: String, fontSize: Float){
    Text(text = message, fontSize = fontSize.sp
        , fontWeight = FontWeight.Bold
    )

}
@Composable
fun CustomList(items: List<String>){
    Column{
        for(item in items){
            Text(text = item)
            Divider(color= Color.DarkGray)
        }
    }
}
@Composable//애너테에션 annotation
fun CustomSwitch() {
    val checked = remember { mutableStateOf(true) }
    Column {
        Switch(checked = checked.value, onCheckedChange ={checked.value=it} )
        if(checked.value){
            Text(text = "Switch is On")
        }else{
            Text(text = "Switch is Off")
        }
    }
    //Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeDemoTheme {
        DemoScreen()
        CustomSwitch()
        CustomList(listOf("1","2","3","4","5"))
    }
}
