package uabc.marce.pdmproyectofinal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uabc.marce.pdmproyectofinal.R

@Composable
fun StartScreen (
  onApiButtonClicked: ()->Unit ={},
  onBdButtonClicked : ()->Unit ={},
  modifier : Modifier = Modifier
){
  Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ){
    Image(
      painter = painterResource(id = R.drawable.edamam_logo),
      contentDescription = null,
      modifier = Modifier
        .fillMaxWidth()
        .height(100.dp),
      contentScale = ContentScale.Crop
    )

    Text(
      text = stringResource(id = R.string.proyecto_final),
      style = MaterialTheme.typography.headlineSmall
    )

    Spacer(modifier = Modifier.weight(1f))

    Button(onClick = {onApiButtonClicked()}){
      Text(
        text = stringResource(id = R.string.api_search),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
      )
    }

    Spacer(modifier = Modifier.height(25.dp))

    Button(onClick = {onBdButtonClicked()}){
      Text(
        text = stringResource(id = R.string.recetas_guardadas),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
      )
    }

    Spacer(modifier = Modifier.weight(1f))

  }
}

@Preview
@Composable
fun StartScreenPreview(){
  StartScreen()
}