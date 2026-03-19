package ramirez.ruben.simulacionpartido.ui.screens

import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramirez.ruben.simulacionpartido.ui.theme.
import ramirez.ruben.simulacionpartido.ui.theme.Pink40
import ramirez.ruben.simulacionpartido.ui.theme.PurpleGrey40
import ramirez.ruben.simulacionpartido.viewmodel.EquipoViewModel

@Composable
fun ListaEquiposScreen(viewModel: EquipoViewModel, onNavigationDetail: (id: Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 5.dp, 20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    )
    {
        items(viewModel.availableTeams()) { equipo ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable(onClick = { onNavigationDetail(equipo.id) })
            ) {
                Box {
                    Image(
                        painter = painterResource(id = equipo.image),
                        contentDescription = equipo.id,
                        modifier = Modifier.size(size = 150.dp),
                        contentScale = ContentScale.Fit
                    )
                    Row(
                        modifier = Modifier
                            .size(width = 35.dp, height = 35.dp)
                            .background(
                                color = PurpleGrey40,
                                shape = RoundedCornerShape(size = 40.dp)
                            )
                            .align(Alignment.TopEnd),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "${equipo.id}",
                            color = Pink40,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Text(text = "${equipo.id}", textAlign = TextAlign.Center, fontSize = 18.sp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ListaEquiposScreenPreview() {
    ListaEquiposScreen(showAllTeams(), onNavigationDetail = { id -> {} })
}