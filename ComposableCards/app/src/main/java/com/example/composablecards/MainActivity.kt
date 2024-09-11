package com.example.composablecards

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composablecards.ui.theme.ComposableCardsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposableCardsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    FourCards(modifier=Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(topic: String, explanation: String, modifier: Modifier = Modifier) {
    Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
        Text(
            text = topic,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = explanation,
            modifier = Modifier,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun SingleCard(topic: String = "meow", explanation: String = "squeak",
               color: Color,
               modifier: Modifier = Modifier){
    val shape = RectangleShape
    Card(
            shape = shape, colors = CardDefaults.cardColors(
            containerColor = color),
            modifier = modifier
    ){
        Greeting(topic,explanation,modifier=Modifier.padding(16.dp))
    }
    CardDefaults.cardColors(
        containerColor = MaterialTheme.colorScheme.surfaceVariant)
}

@Composable
fun FourCards(modifier: Modifier = Modifier){
    val c1 = Color(0xFFEADDFF)
    val c2 = Color(0xFFD0BCFF)
    val c3 = Color(0xFFB69DF8)
    val c4 = Color(0xFFF6EDFF)
    Column (
        modifier = modifier.fillMaxSize()
    ){
        val mod = Modifier
            .fillMaxHeight()
            .weight(1f)
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ){
            SingleCard(stringResource(R.string.t1),stringResource(R.string.e1),color = c1, modifier = mod)
            SingleCard( stringResource(R.string.t2), stringResource(R.string.e2),color = c2, modifier = mod)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            SingleCard(stringResource(R.string.t3),stringResource(R.string.e3),color = c3,modifier = mod)
            SingleCard(stringResource(R.string.t4),stringResource(R.string.e4),color = c4,modifier = mod)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FourCards()
}
