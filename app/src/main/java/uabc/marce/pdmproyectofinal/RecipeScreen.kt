package uabc.marce.pdmproyectofinal

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import uabc.marce.pdmproyectofinal.ui.ApiScreen
import uabc.marce.pdmproyectofinal.ui.BdScreen
import uabc.marce.pdmproyectofinal.ui.DetailScreen
import uabc.marce.pdmproyectofinal.ui.RecipeViewModel
import uabc.marce.pdmproyectofinal.ui.StartScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import uabc.marce.pdmproyectofinal.network.Recipe

// Define las rutas de las vistas
enum class RecipeScreen (@StringRes val title : Int) {
  Start(title = R.string.app_name),
  Api(title = R.string.busqueda_receta),
  Bd(title = R.string.recetas_guardadas),
  Detail(title = R.string.detalle)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
  currentScreen : RecipeScreen,
  canNavigateBack : Boolean,
  navigateUp : ()->Unit,
  modifier : Modifier = Modifier
) {
  TopAppBar(
    title = { Text(stringResource(id = currentScreen.title)) },
    colors = TopAppBarDefaults.mediumTopAppBarColors(
      containerColor = MaterialTheme.colorScheme.primaryContainer
    ),
    modifier = modifier,
    navigationIcon = {
      if (canNavigateBack) {
        IconButton(onClick = navigateUp) {
          Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(R.string.back_button)
          )
        }
      }
    }
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeApp(
  navController : NavHostController = rememberNavController()
) {
  val backStackEntry by navController.currentBackStackEntryAsState()
  val currentScreen = RecipeScreen.valueOf(
    value = backStackEntry?.destination?.route ?: RecipeScreen.Start.name
  )
  val recipeViewModel : RecipeViewModel = RecipeViewModel()
  Scaffold (
    topBar = {
      RecipeAppBar(
        currentScreen = currentScreen,
        canNavigateBack = navController.previousBackStackEntry != null,
        navigateUp = { navController.navigateUp() }
      )
    }
  ) {
    innerPadding ->
    NavHost(
      navController = navController,
      startDestination = RecipeScreen.Start.name,
      modifier = Modifier.padding(innerPadding)
    ) {
      composable(route = RecipeScreen.Start.name) {
        StartScreen(
          onApiButtonClicked = { navController.navigate(RecipeScreen.Api.name) },
          onBdButtonClicked = { navController.navigate(RecipeScreen.Bd.name) }
        )
      }

      composable(route = RecipeScreen.Api.name){
        ApiScreen(
          recipeUiState = recipeViewModel.recipeUiState,
          onRecipeClicked = {navController.navigate(RecipeScreen.Detail.name)}
        )
      }

      composable(route = RecipeScreen.Bd.name){
        BdScreen(
          onRecipeClicked = {navController.navigate(RecipeScreen.Detail.name)}
        )
      }

      composable(route = RecipeScreen.Detail.name){
        DetailScreen(recipe= Recipe(""))
      }
    }
  }
}