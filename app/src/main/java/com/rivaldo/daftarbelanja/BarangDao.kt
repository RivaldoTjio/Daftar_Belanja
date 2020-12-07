package com.rivaldo.daftarbelanja

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BarangDao {

    @Query("Select * from barang_table order by id")
    fun getAllDaftarBelanja(): LiveData<List<Barang>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(barang: Barang)

    @Query("delete from barang_table")
    suspend fun deleteAll()

    @Query("Delete from barang_table where id =:deletedidbarang")
    suspend fun deleteById(deletedidbarang: Int)
}