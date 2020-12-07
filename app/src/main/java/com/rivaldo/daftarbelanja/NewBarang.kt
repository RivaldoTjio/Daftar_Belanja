package com.rivaldo.daftarbelanja

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewBarang : AppCompatActivity() {

    companion object{
        const val EXTRA_REPLY = "com.rivaldo.daftarbelanja.REPLY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_barang)
        val edtNamaBarang = findViewById<EditText>(R.id.edtnamabarang)
        val edtKuantitas = findViewById<EditText>(R.id.edtkuantitas)
        val buttonsimpan = findViewById<Button>(R.id.button)

        buttonsimpan.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(edtNamaBarang.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val barang : Barang = Barang(0, edtNamaBarang.text.toString(), edtKuantitas.text.toString())
                val bundle = Bundle()
                bundle.putString("namabarang",edtNamaBarang.text.toString())
                bundle.putString("kuantitas", edtKuantitas.text.toString())
                replyIntent.putExtra(EXTRA_REPLY, bundle)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}