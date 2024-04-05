package il.co.bringthemhome.data.remoteDb

import il.co.bringthemhome.api.KidnappedApi

class RowRemoteDataSource(
    private val apiService: KidnappedApi
) : BaseDataSource() {
    suspend fun getRows() = getResult { apiService.getKidnapped() }
    suspend fun getReleasedRows() = getResult { apiService.getKidnapped() }
    suspend fun getActivitiesRows() = getResult { apiService.getKidnapped() }
    suspend fun getFilteredNameRows() = getResult { apiService.getKidnapped() }

}