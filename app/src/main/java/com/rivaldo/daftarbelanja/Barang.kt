package com.rivaldo.daftarbelanja

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "barang_table")
data class Barang(@PrimaryKey(autoGenerate = true) val id: Int = 0,
                  @ColumnInfo(name = "namabarang") val namabarang: String,
                  @ColumnInfo(name = "kuantitas") val kuantitas: String)

