package com.example.penjualanmobilkotlin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class DataPaketActivity : AppCompatActivity() {
    private lateinit var listview: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val listData = ArrayList<String>()
    private val kodelist = ArrayList<String>()
    private val uanglist = ArrayList<String>()
    private val tenorlist = ArrayList<String>()
    private val bungalist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_pembeli)

        val tomboltambah=findViewById<Button>(R.id.tomboltambah)

        tomboltambah.setOnClickListener {
            startActivity(Intent(this, TambahPaketActivity::class.java))
        }

        listview=findViewById(R.id.listpaket)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listview.adapter = adapter

        loadData()

        listview.setOnItemClickListener { _, _, position, _ ->
            showOptionDialog(position)

        }
    }

    private fun loadData() {
        val url="http://192.168.1.109/Penjualanmobilkotlinvscode/Tampilpaket.php"
        val request= JsonArrayRequest(url,
            {response->
                listData.clear()
                kodelist.clear()
                uanglist.clear()
                tenorlist.clear()
                bungalist.clear()

                for(i in 0 until response.length()){
                    val obj = response.getJSONObject(i)

                    kodelist.add(obj.getString("kode_paket"))
                    uanglist.add(obj.getString("uang_muka"))
                    tenorlist.add(obj.getString("tenor"))
                    bungalist.add(obj.getString("bunga_cicilan"))

                    listData.add(
                        "Kode_paket : ${obj.getString("kode_paket")}\n"+
                                "Uang_muka : ${obj.getString("uang_muka")}\n"+
                                "Tenor : ${obj.getString("tenor")}\n"+
                                "Bunga_cicilan : ${obj.getString("bunga_cicilan")}\n"
                    )
                }
                adapter.notifyDataSetChanged()
            },
            {
                Toast.makeText(this, "gagal memuat data", Toast.LENGTH_SHORT).show()
            })
        Volley.newRequestQueue(this).add(request)
    }

    private fun showOptionDialog(position: Int) {
        val pilihan = arrayOf("Edit", "Hapus")

        AlertDialog.Builder(this)
            .setTitle("Pilih Aksi")
            .setItems(pilihan) { _, which ->
                when (which) {
                    0 -> {
                        val intent = Intent(this, EditPembeliActivity::class.java)
                        intent.putExtra("kode_paket", kodelist[position])
                        intent.putExtra("uang_muka", uanglist[position])
                        intent.putExtra("tenor", tenorlist[position])
                        intent.putExtra("bunga_cicilan", bungalist[position])

                        startActivityForResult(intent, 2)

                    }
                    1 -> {
                        konfirmasiHapus(position)
                    }
                }
            }
            .show()
    }

    private fun konfirmasiHapus(position: Int) {
        AlertDialog.Builder(this)
            .setTitle("Hapus Data")
            .setMessage("yakin ingin menghapus data ini?")
            .setPositiveButton("ya") { _, _ ->
                hapusData(kodelist[position])
            }
            .setNegativeButton("Batal", null)
            .show()
    }

    private fun hapusData(Kode_paket: String) {
        val url = "http://10.208.184.71/Penjualanmobilkotlinvscode/hapuspaket.php"

        val request = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val json = JSONObject(response)
                Toast.makeText(this, json.getString("message"), Toast.LENGTH_SHORT).show()
                loadData()
            },
            {
                Toast.makeText(this, "gagal koneksi", Toast.LENGTH_SHORT).show()
            }

        ){
            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("kode_paket" to Kode_paket)
            }
        }
        Volley.newRequestQueue(this).add(request)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            loadData()
        }
    }
}