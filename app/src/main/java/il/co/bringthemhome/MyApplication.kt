package il.co.bringthemhome

import android.app.Application
import il.co.bringthemhome.api.RetrofitInstance
import il.co.bringthemhome.data.localDb.RowDatabase
import il.co.bringthemhome.data.remoteDb.RowRemoteDataSource
import il.co.bringthemhome.data.repository.RowRepository

class MyApplication : Application() {
    lateinit var rowRepository: RowRepository
    override fun onCreate() {
        super.onCreate()

        val database = RowDatabase.getDatabase(applicationContext)
        val remoteDataSource = RowRemoteDataSource(RetrofitInstance.api)
        val recordDao = database?.rowDao()

        rowRepository = RowRepository(RetrofitInstance.api, database!!, remoteDataSource, recordDao!!)
    }
}