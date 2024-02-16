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

    @Query("""
    SELECT * FROM rows
    WHERE (:name IS NULL OR :name = '' OR (b || ' ' || c) LIKE '%' || :name || '%' OR (c || ' ' || b) LIKE '%' || :name || '%')
    AND (:minAge IS NULL OR :minAge = '' OR d >= CAST(:minAge AS INTEGER))
    AND (:maxAge IS NULL OR :maxAge = '' OR d <= CAST(:maxAge AS INTEGER))
    AND (:gender IS NULL OR :gender = '' OR e = :gender)
    AND (:city IS NULL OR :city = '' OR f LIKE '%' || :city || '%')
""")
    suspend fun getFilteredRows(name: String?, minAge: String?, maxAge: String?, gender: String?, city: String?): List<Row>



}