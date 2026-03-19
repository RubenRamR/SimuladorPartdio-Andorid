package ramirez.ruben.simulacionpartido.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import ramirez.ruben.simulacionpartido.data.models.Equipo
import kotlin.random.Random


class EquipoViewModel: ViewModel() {

    private val availableTeams = listOf<Equipo>(
        Equipo(1,"Chivas", 10),
        Equipo(2,"America", 30),
        Equipo(3,"Cruz Azul", 30),
        Equipo(4,"Atlas", 30),
        Equipo(5,"Bayern Munchen", 30),
        Equipo(6,"Manchester United", 30),
        Equipo(7,"Pumas", 30),
        Equipo(8,"Real Madrid", 30),
        Equipo(9,"FC. Barcelona", 30),
        Equipo(10,"Borussia Dortmund", 30),
    )

    var resultado by mutableStateOf<Equipo?>(null)
        private set

    fun retornarEquipos(): List<Equipo>{
        return availableTeams
    }

    fun detalleEquipo(){

    }

    fun simularPartido(e1: Equipo, e2: Equipo){

        val totalPuntos = e1.points + e1.points
        val random = (0..totalPuntos).random()

        if(e1.points < random){

        }
    }






}
