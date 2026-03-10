package com.example.penjualanmobilkotlin

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BerandaMActivity : AppCompatActivity() {
    private lateinit var btnCash: Button
    private lateinit var btnKredit: Button
    private lateinit var btnCililan: Button
    private lateinit var btnLogout: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_berandam)

        // Inisialisasi semua tombol menggunakan sintaks findViewById yang benar
        btnCash = findViewById(R.id.tombolbayarcash)
        btnKredit = findViewById(R.id.tombolbelikredit)
        btnCililan = findViewById(R.id.tombolbayarcicil)
        btnLogout = findViewById(R.id.tombollogout)

        // Tambahkan OnClickListener menggunakan lambda expression (cara Kotlin yang ringkas)
        btnCash.setOnClickListener {
            val intent = Intent(this@BerandaMActivity, LaporanCashActivity::class.java)
            startActivity(intent)
        }

        btnKredit.setOnClickListener {
            val intent = Intent(this@BerandaMActivity, LaporanKreditActivity::class.java)
            startActivity(intent)
        }

        btnCililan.setOnClickListener {
            val intent = Intent(this@BerandaMActivity, LaporanCicilanActivity::class.java)
            startActivity(intent)
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this@BerandaMActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}