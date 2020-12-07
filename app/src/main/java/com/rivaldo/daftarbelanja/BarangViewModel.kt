package com.rivaldo.daftarbelanja

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class BarangViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: BarangRepository

    val allBarang: LiveData<List<Barang>>

    init {
        val barangDao = BarangRoomDatabase.getDatabase(application, viewModelScope).barangDao()
        repository = BarangRepository(barangDao)
        allBarang = repository.allBarang

    }

    fun insert(barang: Barang) = viewModelScope.launch {
        repository.insert(barang)
    }

    fun deleteById(id : Int) = viewModelScope.launch {
        repository.deleteById(id)
    }

}