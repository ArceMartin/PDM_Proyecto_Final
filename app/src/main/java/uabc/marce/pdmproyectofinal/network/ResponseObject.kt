package uabc.marce.pdmproyectofinal.network

import kotlinx.serialization.Serializable

@Serializable
data class ResponseObject(
  val hits: List<Hit>
)