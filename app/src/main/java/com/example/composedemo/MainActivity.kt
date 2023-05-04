package com.example.composedemo

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

        DemoSldier(sliderPosition = sliderPosition, onPositionChange = handlePositionChange)

        Text(
            style = MaterialTheme.typography.h2,
            text = sliderPosition.toInt().toString() + "sp"
        )
    }
}

@Composable
fun DemoSldier(sliderPosition: Float, onPositionChange:(Float)->Unit){
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
@Composable//애너테에션 annotation
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ComposeDemoTheme {
        DemoScreen()
    }
}
