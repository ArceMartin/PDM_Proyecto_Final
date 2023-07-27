package uabc.marce.pdmproyectofinal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import uabc.marce.pdmproyectofinal.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import uabc.marce.pdmproyectofinal.network.Recipe


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ApiScreen(
  recipeUiState : RecipeUiState,
  onRecipeClicked : ()->Unit,
  modifier : Modifier = Modifier
) {
  var expanded by remember { mutableStateOf(false)}
  val options = mapOf(
    "pollo"       to "chicken",
    "azucar"      to "sugar",
    "sal"         to "salt",
    "huevo"       to "egg",
    "arroz"       to "rice",
    "cebolla"     to "onion",
    "zanahoria"   to "carrot",
    "carne"       to "meat",
    "papa"        to "potato",
    "mantequilla" to "butter")
  var selectedItem by remember { mutableStateOf("pollo")}
  var textFiledSize by remember { mutableStateOf(Size.Zero) }
  val icon = if(expanded) { Icons.Filled.KeyboardArrowUp } else { Icons.Filled.KeyboardArrowDown}

  Column (
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    // --------------------------- SPACER ----------------------------

    Spacer(modifier = Modifier.height(150.dp))

    // --------------------- TEXTO "INGREDIENTE" ---------------------

    Text(
      text= stringResource(id = R.string.ingrediente),
      style = MaterialTheme.typography.headlineSmall
    )

    // ------------------------ MENÚ DROPDOWN ------------------------

    Column(modifier = Modifier.padding(20.dp)) {
      OutlinedTextField(
        value = selectedItem,
        onValueChange = {selectedItem = it},
        modifier = Modifier
          .fillMaxWidth()
          .onGloballyPositioned { coordinates ->
            textFiledSize = coordinates.size.toSize()
          },
        label = {Text(text = stringResource(id = R.string.selecciona_ingrediente))},
        trailingIcon = {  Icon(icon, "", Modifier.clickable {expanded = !expanded} ) }
      )

      DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.width(with(LocalDensity.current){textFiledSize.width.toDp()})
      ) {
        options.forEach { label ->
          DropdownMenuItem(
            text = { Text(text=label.key) },
            onClick = {
              selectedItem = label.key
              expanded = false
            }
          )
        }
      }
    }

    // ----------------------- BOTÓN "BUSCAR" ------------------------

    Button(onClick = {}) {
      Text(text= stringResource(id = R.string.buscar),style = MaterialTheme.typography.labelMedium)
    }

    // --------------- RESULTADO DE SOLICITUD RETROFIT ---------------

    /* Considera recipeUiState, el estado del ultimo API query
       Llama a una funcion composable dependiendo del valor. */
    when(recipeUiState){
      is RecipeUiState.Success -> ResultScreen(
        recipes         = recipeUiState.recipes,
        onRecipeClicked = onRecipeClicked,
        modifier        = Modifier.fillMaxSize()
      )
      is RecipeUiState.Error   -> ErrorScreen(modifier=modifier.fillMaxSize())
      is RecipeUiState.Loading -> LoadingScreen(modifier=modifier.fillMaxSize())
    }
  }
}

@Composable
fun ResultScreen(
  modifier        : Modifier = Modifier,
  recipes         : List<Recipe>,
  onRecipeClicked : ()->Unit = {}
) {
  //  Text displaying number of recipes retrieved
  /*
  Box(contentAlignment = Alignment.Center, modifier = modifier) {
    Text(text = recipes.size.toString(), modifier = Modifier.clickable { onRecipeClicked() })
  }
  */
  LazyColumn {
    items(recipes) {recipe ->
      Card(modifier = Modifier.padding(5.dp).fillMaxWidth().clickable { onRecipeClicked() }) {
        Text(text = recipe.label, style = MaterialTheme.typography.displaySmall) // NOMBRE DE LA RECETA
      }
    }
  }
}

@Composable
fun LoadingScreen(modifier : Modifier = Modifier) {
  Image(
    painter = painterResource(id = R.drawable.loading_img),
    contentDescription = stringResource(id = R.string.loading),
    modifier = modifier.size(200.dp)
  )
}


@Composable
fun ErrorScreen(modifier : Modifier = Modifier) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier
  ) {
    Image(
      painter = painterResource(id = R.drawable.ic_connection_error),
      contentDescription = ""
    )
    Text(
      text = stringResource(id = R.string.loading_failed),
      modifier = Modifier.padding(16.dp)
    )
  }
}
/*
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun DropDownMenu()  {
    var expanded by remember { mutableStateOf(false)}
    val options = mapOf(
      "pollo"       to "chicken",
      "azucar"      to "sugar",
      "sal"         to "salt",
      "huevo"       to "egg",
      "arroz"       to "rice",
      "cebolla"     to "onion",
      "zanahoria"   to "carrot",
      "carne"       to "meat",
      "papa"        to "potato",
      "mantequilla" to "butter")
    var selectedItem by remember { mutableStateOf("pollo")}
    var textFiledSize by remember { mutableStateOf(Size.Zero) }
    val icon = if(expanded) { Icons.Filled.KeyboardArrowUp } else { Icons.Filled.KeyboardArrowDown}

    Column(modifier = Modifier.padding(20.dp)) {
      OutlinedTextField(
        value = selectedItem,
        onValueChange = {selectedItem = it},
        modifier = Modifier
          .fillMaxWidth()
          .onGloballyPositioned { coordinates ->
            textFiledSize = coordinates.size.toSize()
          },
        label = {Text(text = stringResource(id = R.string.selecciona_ingrediente))},
        trailingIcon = {  Icon(icon, "", Modifier.clickable {expanded = !expanded} ) }
      )

      DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier.width(with(LocalDensity.current){textFiledSize.width.toDp()})
      ) {
        options.forEach { label ->
          DropdownMenuItem(
            text = { Text(text=label.key) },
            onClick = {
              selectedItem = label.key
              expanded = false
            }
          )
        }
      }
    }
  }
*/