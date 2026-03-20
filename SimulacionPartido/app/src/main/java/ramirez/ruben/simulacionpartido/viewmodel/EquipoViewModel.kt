package ramirez.ruben.simulacionpartido.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ramirez.ruben.simulacionpartido.R
import ramirez.ruben.simulacionpartido.data.models.Equipo
import kotlin.random.Random


class EquipoViewModel: ViewModel() {

    private val availableTeams = listOf<Equipo>(
        Equipo(1,"Chivas", 10, R.drawable.chivas),
        Equipo(2,"America", 30, R.drawable.america),
        Equipo(3,"Cruz Azul", 20, R.drawable.cruzazul),
        Equipo(4,"Atlas", 35, R.drawable.atlas),
        Equipo(5,"Bayern Munchen", 60, R.drawable.bayern),
        Equipo(6,"Manchester United", 70, R.drawable.manchester),
        Equipo(7,"Pumas", 40, R.drawable.pumas),
        Equipo(8,"Real Madrid", 90, R.drawable.realmadrid),
        Equipo(9,"FC. Barcelona", 80, R.drawable.barcelona),
        Equipo(10,"Borussia Dortmund", 40, R.drawable.borussia),
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

    // funcion seleccionar equipo (se setea al seleccionar un equipo)
    fun seleccionarEquipo(equipo: Equipo) {
        equipoSeleccionado = equipo
        resultado = ""
    }

    fun simularPartido() {
        // si se selecciona un equipo permite lo siguiente
        equipoSeleccionado?.let { equipo ->

            // filtramos al equipo que se selecciono para obtener un rival aleatorio (para que no sea posible que elija el mismo)
            val rival = equipos.filter { it.id != equipo.id }.random()

            // probabilidad de que sea empate (20% no viene como tal pero se lo agrege)
            val empateProb = 20

            // sumamos los puntos de los 2 equipos
            val totalPuntos = equipo.points + rival.points

            //calculamos la probabilidad de cada equipo tomando en cuenta la probabilidad de empate y la cantidad total de puntos para obtener su respectivo %
            val probEquipo = (equipo.points * (100 - empateProb)) / totalPuntos
            val probRival = (rival.points * (100 - empateProb)) / totalPuntos

            // obtenemos un valor aleatorio de 0 a 100
            val random = (0..100).random()

            // regresar resultado (String)
            resultado = when {
                random < probEquipo ->
                    "${equipo.name} le gana a ${rival.name}"

                random < probEquipo + probRival ->
                    "${rival.name} le gana a ${equipo.name}"

                else ->
                    "${equipo.name} empata con ${rival.name}"
            }
        }
    }






}
