package com.rivaldo.daftarbelanja

import androidx.lifecycle.LiveData

class BarangRepository(private val barangDao: BarangDao) {
    val allBarang: LiveData<List<Barang>> = barangDao.getAllDaftarBelanja()

    suspend fun insert(barang: Barang) {
        barangDao.insert(barang)
    }

    suspend fun deleteById(id : Int){
        barangDao.deleteById(id)
    }
}