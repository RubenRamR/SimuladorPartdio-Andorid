package ramirez.ruben.simulacionpartido.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ramirez.ruben.simulacionpartido.ui.screens.DetalleEquipoScreen
import ramirez.ruben.simulacionpartido.ui.screens.ListaEquiposScreen
import ramirez.ruben.simulacionpartido.viewmodel.EquipoViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val equipoViewModel: EquipoViewModel = viewModel()

    NavHost(navController = navController, startDestination = "lista_equipos") {

        composable("lista_equipos") {
            ListaEquiposScreen(
                viewModel = equipoViewModel,
                onNavigationDetail = {
                    navController.navigate("detalle_equipo")
                }
            )
        }

        composable("detalle_equipo") {
            DetalleEquipoScreen(
                viewModel = equipoViewModel
            )
        }
    }
}