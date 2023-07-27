package uabc.marce.pdmproyectofinal.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BdScreen (
  onRecipeClicked : ()->Unit
){
  LazyColumn (
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    items(50) { index ->
      Card(
          modifier = Modifier.padding(5.dp).fillMaxWidth().clickable{ onRecipeClicked() }
        ) {
        Text(
          text = "Item: $index",
          style = MaterialTheme.typography.labelLarge
        )
      }
    }
  }
}

@Preview
@Composable
fun BdScreenPreview() {
  BdScreen({})
}