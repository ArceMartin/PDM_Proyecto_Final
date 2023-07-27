package uabc.marce.pdmproyectofinal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uabc.marce.pdmproyectofinal.network.Recipe

@Composable
fun DetailScreen (
  recipe : Recipe
) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    Text(text = recipe.label, style = MaterialTheme.typography.labelMedium)

    Spacer(modifier = Modifier.height(15.dp))

  }
}

@Preview
@Composable
fun DetailScreenPreview() {
  DetailScreen(Recipe("Sopa"))
}