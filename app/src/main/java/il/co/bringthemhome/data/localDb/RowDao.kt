package il.co.bringthemhome.data.localDb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import il.co.bringthemhome.data.models.Row

@Dao
interface RowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRow (row : List<Row>)

    @Query("DELETE FROM rows")
    fun clearRows()

    @Query("SELECT * FROM rows")
    fun getRows(): LiveData<List<Row>>

    @Query("SELECT * FROM rows WHERE status = '2'")
    fun getReleasedRows(): LiveData<List<Row>>

    @Query("SELECT * FROM rows WHERE status = '1'")
    fun getActivitiesRows(): LiveData<List<Row>>


}