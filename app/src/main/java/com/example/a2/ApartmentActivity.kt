package com.example.a2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ApartmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_apartment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.miHome -> {
                startActivity(Intent(this, DetachedHouseActivity::class.java))
            }
            R.id.miSemiHome -> {
                startActivity(Intent(this, SemiDetachedActivity::class.java))
            }
            R.id.miCondo -> {
                startActivity(Intent(this, CondoActivity::class.java))
            }
            R.id.miApt -> {
                startActivity(Intent(this, ApartmentActivity::class.java))
            }
            R.id.miTownHome -> {
                startActivity(Intent(this, TownHouseActivity::class.java))
            }
        }
        return true
    }
}