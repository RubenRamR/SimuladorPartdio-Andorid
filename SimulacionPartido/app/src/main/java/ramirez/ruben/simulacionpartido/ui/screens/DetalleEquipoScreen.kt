package ramirez.ruben.simulacionpartido.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramirez.ruben.simulacionpartido.viewmodel.EquipoViewModel

@Composable
fun DetalleEquipoScreen(viewModel: EquipoViewModel) {
    val equipo = viewModel.equipoSeleccionado
    val rival = viewModel.rivalSeleccionado
    val resultado = viewModel.resultado

    if (equipo == null || rival == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text("Error: No se ha seleccionado un equipo completo.")
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Simulación de Partido",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        // Fila para mostrar Equipo VS Rival
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tu Equipo
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = equipo.logo),
                    contentDescription = equipo.name,
                    modifier = Modifier.size(100.dp)
                )
                Text(text = equipo.name, fontWeight = FontWeight.Bold)
                Text(text = "${equipo.points} pts")
            }

            Text(text = "VS", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)

            // Rival Aleatorio
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = rival.logo),
                    contentDescription = rival.name,
                    modifier = Modifier.size(100.dp)
                )
                Text(text = rival.name, fontWeight = FontWeight.Bold)
                Text(text = "${rival.points} pts")
            }
        }

        // Botón de Simulación
        Button(onClick = { viewModel.simularPartido() }) {
            Text(text = "Simular partido")
        }

        // Mostrar Resultado si existe
        if (resultado.isNotEmpty()) {
            Text(
                text = resultado,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetalleEquipoScreenPreview() {
    val mockViewModel = EquipoViewModel()

    val equipoDePrueba = mockViewModel.equipos.first()
    mockViewModel.seleccionarEquipo(equipoDePrueba)

    DetalleEquipoScreen(viewModel = mockViewModel)
}