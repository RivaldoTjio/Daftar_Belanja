package com.rivaldo.daftarbelanja

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Barang::class), version = 1, exportSchema = false)
public abstract class BarangRoomDatabase : RoomDatabase(){
    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var INSTANCE: BarangRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope) :BarangRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BarangRoomDatabase::class.java,
                    "barang_database"
                )
                    .addCallback(BarangDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class BarangDatabaseCallback(private val scope: CoroutineScope) :RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        //populateDatabase(database.barangDao())
                    }
                }
        }

//            suspend fun populateDatabase(barangDao: BarangDao) {
//                barangDao.insert()
//            }
    }
}