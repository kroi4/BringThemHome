package il.co.bringthemhome.data.localDb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import il.co.bringthemhome.data.models.Row

@Database(entities = [Row::class], version = 1, exportSchema = false)
abstract class RowDatabase : RoomDatabase() {

    abstract fun rowDao(): RowDao

    companion object{
        @Volatile
        private var INSTANCE: RowDatabase? = null

        fun getDatabase(context: Context) : RowDatabase? {
            if (INSTANCE == null){
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        RowDatabase::class.java,
                        "rows_database"
                    ).fallbackToDestructiveMigration().build()
                }
            }

            return INSTANCE!!
        }
    }
}