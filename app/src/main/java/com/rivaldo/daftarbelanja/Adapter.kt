package com.rivaldo.daftarbelanja

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class Adapter internal constructor(context: Context) : RecyclerView.Adapter<Adapter.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var barang = emptyList<Barang>()
    private var ItemClickCallback: onItemClickCallback? = null

    // This property is only valid between onCreateView and
// onDestroyView.


    fun setOnClickCallback(onItemClickCallback: onItemClickCallback?) {
        this.ItemClickCallback = onItemClickCallback
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namabarang: TextView = itemView.findViewById(R.id.txt_barang)
        val kuantitas: TextView = itemView.findViewById(R.id.txt_kuantitas)
        val img: ImageView = itemView.findViewById(R.id.imgclose)
        fun bind(barangitems: Barang) {

                namabarang.text = barangitems.namabarang
                kuantitas.text = barangitems.kuantitas
                img.setOnClickListener {
                    ItemClickCallback?.onItemDeleteClicked(barangitems.id)
                }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.barangitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(barang[position])
    }

    internal fun setBarang(itembarang: List<Barang>) {
        this.barang = itembarang
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = barang.size

    interface onItemClickCallback {
        fun onItemDeleteClicked(id: Int)
    }
}