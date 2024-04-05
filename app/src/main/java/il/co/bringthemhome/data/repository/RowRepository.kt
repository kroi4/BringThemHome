package il.co.bringthemhome.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import il.co.bringthemhome.data.localDb.RowDao
import il.co.bringthemhome.data.localDb.RowDatabase
import il.co.bringthemhome.data.models.Kidnapped
import il.co.bringthemhome.data.models.Row
import il.co.bringthemhome.data.remoteDb.RowRemoteDataSource
import il.co.bringthemhome.utils.performFetchingAndSaving
import kotlinx.coroutines.runBlocking

class RowRepository(
    private val rowDatabase: RowDatabase,
    private val remoteDatasource: RowRemoteDataSource,
    private val localDatasource: RowDao
) {

    private val rowsLiveData = MutableLiveData<Kidnapped>()

    val rows: LiveData<Kidnapped>
        get() = rowsLiveData

    fun getAllRows() = performFetchingAndSaving(
        { localDatasource.getRows() },
        { remoteDatasource.getRows() },
        { localDatasource.clearRows() },
        { localDatasource.insertRow(it.rows) }
    )

    fun getReleasedRows() = performFetchingAndSaving(
        { localDatasource.getReleasedRows() },
        { remoteDatasource.getReleasedRows() },
        { localDatasource.clearRows() },
        { localDatasource.insertRow(it.rows) }
    )

    fun getActivitiesRows() = performFetchingAndSaving(
        { localDatasource.getActivitiesRows() },
        { remoteDatasource.getActivitiesRows() },
        { localDatasource.clearRows() },
        { localDatasource.insertRow(it.rows) }
    )

    fun getFilteredRows(
        name: String?, minAge: String?, maxAge: String?, gender: String?, city: String?, status: String?
    ): List<Row> = runBlocking {
        rowDatabase.rowDao().getFilteredRows(
            name, minAge, maxAge, gender, city, status
        )
    }

    fun clearRows() {
        rowDatabase.rowDao().clearRows()
    }

    fun getAllCountRows(): Int = runBlocking {
        rowDatabase.rowDao().getAllCountRows()
    }

    fun getReleasedCountRows(): Int = runBlocking {
        rowDatabase.rowDao().getReleasedCountRows()
    }

    fun getActivitiesCountRows(): Int = runBlocking {
        rowDatabase.rowDao().getActivitiesCountRows()
    }
}