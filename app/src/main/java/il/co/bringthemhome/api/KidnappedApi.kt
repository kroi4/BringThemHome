package il.co.bringthemhome.api

import il.co.bringthemhome.data.models.Kidnapped
import retrofit2.Response
import retrofit2.http.GET

interface KidnappedApi {

    @GET("website/data.json")
    suspend fun getKidnapped(): Response<Kidnapped>

}