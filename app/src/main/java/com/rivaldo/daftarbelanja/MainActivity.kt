package com.rivaldo.daftarbelanja

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    private lateinit var barangViewModel: BarangViewModel
    private val newWordActivityRequestCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = Adapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        barangViewModel = ViewModelProvider(this).get(BarangViewModel::class.java)
        barangViewModel.allBarang.observe(this, Observer { barang ->
            barang?.let { adapter.setBarang(it) }

        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewBarang::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }

        adapter.setOnClickCallback(object : Adapter.onItemClickCallback{
            override fun onItemDeleteClicked(id: Int) {
                barangViewModel.deleteById(id)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //menangkap data yang di set oleh NewWordActivity
        if(requestCode == newWordActivityRequestCode && resultCode == Activity.RESULT_OK) {
            val bundle = data?.getBundleExtra(NewBarang.EXTRA_REPLY)


                val barang = Barang(0,bundle?.getString("namabarang").toString(), bundle?.getString("kuantitas").toString())
                barangViewModel.insert(barang)

        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}