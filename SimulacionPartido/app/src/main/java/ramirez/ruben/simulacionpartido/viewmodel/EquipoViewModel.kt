package ramirez.ruben.simulacionpartido.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ramirez.ruben.simulacionpartido.R
import ramirez.ruben.simulacionpartido.data.models.Equipo
import kotlin.random.Random


class EquipoViewModel : ViewModel() {

    private val availableTeams = listOf<Equipo>(
        Equipo(1, "Chivas", 10, R.drawable.chivas),
        Equipo(2, "America", 30, R.drawable.america),
        Equipo(3, "Cruz Azul", 20, R.drawable.cruzazul),
        Equipo(4, "Atlas", 35, R.drawable.atlas),
        Equipo(5, "Bayern Munchen", 60, R.drawable.bayern),
        Equipo(6, "Manchester United", 70, R.drawable.manchester),
        Equipo(7, "Pumas", 40, R.drawable.pumas),
        Equipo(8, "Real Madrid", 90, R.drawable.realmadrid),
        Equipo(9, "FC. Barcelona", 80, R.drawable.barcelona),
        Equipo(10, "Borussia Dortmund", 40, R.drawable.borussia),
    )

    //equipos disponibles
    var equipos by mutableStateOf(availableTeams)
        private set

    //equipo seleccionado
    var equipoSeleccionado by mutableStateOf<Equipo?>(null)
        private set

    //resultado (string)
    var resultado by mutableStateOf("")
        private set

    // Estado para el rival
    var rivalSeleccionado by mutableStateOf<Equipo?>(null)
        private set

    // funcion seleccionar equipo (se setea al seleccionar un equipo)
    fun seleccionarEquipo(equipo: Equipo) {
        equipoSeleccionado = equipo
        rivalSeleccionado = equipos.filter { it.id != equipo.id }.random()
        resultado = ""
    }

    fun simularPartido() {
        val equipo = equipoSeleccionado
        val rival = rivalSeleccionado

        if (equipo != null && rival != null) {

            //Calculamos puntos totales
            val totalPuntos = equipo.points + rival.points

            //Probabilidad
            val probPuraEquipo = (equipo.points * 100) / totalPuntos

            //Número aleatorio (1 a 100)
            val random = (1..100).random()

            // Margen de empate (10% de probabilidad fija en el medio)
            val limiteVictoriaEquipo = probPuraEquipo - 5
            val limiteVictoriaRival = probPuraEquipo + 5

            //Evaluamos el resultado y actualizamos el estado 'resultado'
            resultado = when {
                random <= limiteVictoriaEquipo ->
                    "${equipo.name} le gana a ${rival.name}"

                random > limiteVictoriaEquipo && random <= limiteVictoriaRival ->
                    "${equipo.name} empata con ${rival.name}"

                else ->
                    "${rival.name} le gana a ${equipo.name}"
            }
        } else {
            resultado = "Error de carga."
        }
    }


}
