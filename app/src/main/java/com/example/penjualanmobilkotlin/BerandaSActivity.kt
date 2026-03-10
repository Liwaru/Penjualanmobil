package com.example.penjualanmobilkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BerandaSActivity : AppCompatActivity() {

    private lateinit var btnMobil: Button
    private lateinit var btnPembeli: Button
    private lateinit var btnPaket: Button
    private lateinit var btnCash: Button
    private lateinit var btnKredit: Button
    private lateinit var btnCicilan: Button
    private lateinit var btnLogout: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berandas)

        btnMobil = findViewById(R.id.tombolmobil)
        btnPembeli = findViewById(R.id.tombolpembeli)
        btnPaket = findViewById(R.id.tombolpaket)
        btnCash = findViewById(R.id.tombolbeli)
        btnKredit = findViewById(R.id.tombolkredit)
        btnCicilan = findViewById(R.id.tombolcicil)
        btnLogout = findViewById(R.id.tombollogout)

        btnMobil.setOnClickListener {
            startActivity(Intent(this, DataMobilActivity::class.java))
        }

        btnPembeli.setOnClickListener {
            startActivity(Intent(this, DataPembeliActivity::class.java))
        }

        btnPaket.setOnClickListener {
            startActivity(Intent(this, DataPaketActivity::class.java))
        }

        btnCash.setOnClickListener {
            startActivity(Intent(this, DataCashActivity::class.java))
        }

        btnKredit.setOnClickListener {
            startActivity(Intent(this, DataKreditActivity::class.java))
        }

        btnCicilan.setOnClickListener {
            startActivity(Intent(this, DataCicilActivity::class.java))
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}