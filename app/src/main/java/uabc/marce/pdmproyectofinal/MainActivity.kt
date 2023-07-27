package uabc.marce.pdmproyectofinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import uabc.marce.pdmproyectofinal.ui.theme.PDMProyectoFinalTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      PDMProyectoFinalTheme {

        RecipeApp()

      }
    }
  }
}
