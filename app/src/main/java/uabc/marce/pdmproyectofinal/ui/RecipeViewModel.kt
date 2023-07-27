package uabc.marce.pdmproyectofinal.ui

import android.app.ActivityManager.RecentTaskInfo
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uabc.marce.pdmproyectofinal.network.Recipe
import uabc.marce.pdmproyectofinal.network.RecipeApi
import java.io.IOException

sealed interface RecipeUiState {
  data class Success(val recipes : List<Recipe>) : RecipeUiState
  object     Error                               : RecipeUiState
  object     Loading                             : RecipeUiState
}

class RecipeViewModel() : ViewModel() {
  // Estado mutable que guarda la solicitud al api mas reciente
  var recipeUiState : RecipeUiState by mutableStateOf(RecipeUiState.Loading)
    private set

  /*fun inicia(){getRecipes(ingrediente)}*/
  init { getRecipes() }

  private fun getRecipes() {
    viewModelScope.launch {
      recipeUiState = try{
        val listResult = RecipeApi.retrofitService.getRecipes(
          app_id  = "1e499f55",
          app_key = "5871147d846747babb9a458d6d465f03",
          type    =  "public",
          q       =  "chicken"
        )
        val listaRecetas : List<Recipe> = (listResult.hits).map{it.recipe}
        RecipeUiState.Success(recipes = listaRecetas)
      }
      catch(e : IOException) {
        RecipeUiState.Error
      }
    }
  }
}