package com.example.penjualanmobilkotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class TambahPembeliActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_pembeli)

        val editKtp = findViewById<EditText>(R.id.editktp)
        val editNama = findViewById<EditText>(R.id.editnama)
        val editAlamat = findViewById<EditText>(R.id.editalamat)
        val editNoHp = findViewById<EditText>(R.id.editnohp)
        val btnSimpan = findViewById<Button>(R.id.tombolsimpan)

        btnSimpan.setOnClickListener {
            val ktp = editKtp.text.toString()
            val nama = editNama.text.toString()
            val alamat = editAlamat.text.toString()
            val nohp = editNoHp.text.toString()

            if (ktp.isEmpty() || nama.isEmpty() || alamat.isEmpty() || nohp.isEmpty()) {
                Toast.makeText(this, "Semua data wajib diisi", Toast.LENGTH_SHORT).show()
            } else {
                simpanData(ktp, nama, alamat, nohp)
            }
        }
    }

    private fun simpanData(
        ktp: String,
        nama: String,
        alamat: String,
        nohp: String
    ) {
        val url = "http://192.168.0.15/penjualanmobil/Tambahpembeli.php"

        val request = object : StringRequest(
            Request.Method.POST, url,
            { response ->
                val json = JSONObject(response)
                val success = json.getBoolean("success")
                val message = json.getString("message")

                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

                if(success){
                    setResult(RESULT_OK)
                }
            },
            { error ->
                Toast.makeText(this, "Gagal kembali ke server", Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): MutableMap<String, String> {
                val params = HashMap<String, String>()
                params["ktp"]=ktp
                params["nama_pembeli"]=nama
                params["alamat_pembeli"]=alamat
                params["telp_pembeli"]=nohp
                return params
            }
        }

        Volley.newRequestQueue(this).add(request)
    }
}