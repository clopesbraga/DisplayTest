package br.com.gertec.displaytest.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.gertec.displaytest.viewmodel.DisplayViewModel
import kotlinx.coroutines.delay

@Composable
fun DisplayScreen(navController: NavHostController) {

    val context = LocalContext.current
    val quadrado = remember { mutableStateListOf(*List(5) { false }.toTypedArray()) }
    val todosQuadrados = derivedStateOf { quadrado.all { it } }

    val viewModel by remember { mutableStateOf(DisplayViewModel()) }
    val elementos by viewModel.uiElementoState.collectAsState()
    var showButton by remember { mutableStateOf(false) }
    var buttonText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(5),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp)
        ) {
            items(quadrado.size) { index ->
                val isSelected = quadrado[index]
                val color by animateColorAsState(
                    targetValue = if (isSelected) Color.Green else Color.Gray
                )
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .background(color)
                        .clickable {
                            quadrado[index] = !isSelected
                        }
                ) {
                    viewModel.verificaElementosNaTela(todosQuadrados)
                    LaunchedEffect(Unit) {
                        delay(10000)
                        elementos.let {
                            showButton = true
                            buttonText = if (it) "SUCESSO" else "FALHA"
                        }
                    }

                }

            }

        }
    }
    if (showButton) {

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter

        ) {
            Spacer(modifier = Modifier.height(750.dp))
            ButtonActions(navController,buttonText)
        }
    }
}


@Composable
fun ButtonActions(navController: NavHostController, text: String) {

    var buttoncolor = if (text == "SUCESSO") Color.Green else Color.Red

    Button(
        onClick = { navController.navigate("main_screen") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = ButtonDefaults.buttonColors(buttoncolor)
    ) {
        Text(text)
    }

}




