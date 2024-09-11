package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeStand(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun Title(modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFBEC5D)),
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Lemonade",
                modifier = Modifier
                    .padding(6.dp),
                fontWeight = FontWeight.Bold
            )
        }
    }
}



@Composable
fun LemonadeStand(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(0) }
    var sq by remember { mutableIntStateOf((2..4).random()) }
    val imageResource = when(result) {
        0 -> R.drawable.lemon_tree
        in 1..sq -> R.drawable.lemon_squeeze
        sq+1 -> R.drawable.lemon_drink
        sq+2 -> R.drawable.lemon_restart
        else -> R.drawable.lemon_restart
    }
    val lemonText = when(result) {
        0 -> R.string.tap_lemon_tree
        in 1..sq -> R.string.keep_tapping_squeeze
        sq+1 -> R.string.tap_lemonade_to_drink
        sq+2 -> R.string.tap_empty_glass
        else -> R.string.tap_empty_glass
    }

    val onClick = {
        result = (result+1)%(4+sq-2)
        if (result == 0) sq = (2..4).random()
    }

    Column(modifier) {
        Title(modifier = Modifier.weight(1f))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(15f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC1E1C1)),
                shape = RoundedCornerShape(20)
            ) {
                Image(
                    painter = painterResource(imageResource),
                    contentDescription = stringResource(id = R.string.lemon_tree))
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(lemonText),
                modifier = Modifier,
                fontSize = 18.sp
            )

        }

    }

}

@Preview(showBackground = true)
@Composable
fun LemonadeStandPreviewPreview() {
    LemonadeTheme {
        LemonadeStand()
    }
}