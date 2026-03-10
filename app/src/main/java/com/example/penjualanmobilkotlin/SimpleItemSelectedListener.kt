package com.example.penjualanmobilkotlin

import android.view.View
import android.widget.AdapterView

class SimpleItemSelectedListener(private val callback: Runnable) :
    AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        callback.run()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}

