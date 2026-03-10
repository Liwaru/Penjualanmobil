package com.example.penjualanmobilkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import coil.load

class MobilAdapter(
    private val context: Context,
    private val data: ArrayList<HashMap<String, String>>
) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Any = data[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_mobil, parent, false)

        val img = view.findViewById<ImageView>(R.id.imageMobil)
        val merk = view.findViewById<TextView>(R.id.textMerk)
        val type = view.findViewById<TextView>(R.id.textType)
        val warna = view.findViewById<TextView>(R.id.textWarna)
        val harga = view.findViewById<TextView>(R.id.textHarga)

        val item = data[position]

        merk.text = item["merk"]
        type.text = "Tipe: ${item["type"]}"
        warna.text = "Warna: ${item["warna"]}"
        harga.text = "Harga: Rp ${item["harga"]}"

        val foto = item["foto"]

        img.load(foto) {
            placeholder(R.drawable.ic_launcher_background)
            error(R.drawable.ic_launcher_background)
        }
        return view
    }
}