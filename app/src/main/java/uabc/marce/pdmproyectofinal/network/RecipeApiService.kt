package uabc.marce.pdmproyectofinal.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// Base url
private const val BASE_URL = "https://api.edamam.com"

// Especificamos convertidor json que ignora llaves desconocidas
val json = Json{ignoreUnknownKeys = true}

// Retrofit builder (indica a retrofit que hacer con la info recolectada)
@OptIn(ExperimentalSerializationApi::class)
private val retrofit : Retrofit = Retrofit
  .Builder()
  .baseUrl(BASE_URL)
  .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
  .build()

// Interface define como comunicarse con el servidor
interface RecipeApiService {
  @GET("/api/recipes/v2")
  suspend fun getRecipes(
    @Query("?app_id") app_id  : String,
    @Query("app_key") app_key : String,
    @Query("type")    type    : String,
    @Query("q")       q       : String
  ) : ResponseObject
}

// Exponemos servicio usando singleton para conservar recursos
object RecipeApi {
  val retrofitService : RecipeApiService by lazy {
    retrofit.create(RecipeApiService::class.java)
  }
}