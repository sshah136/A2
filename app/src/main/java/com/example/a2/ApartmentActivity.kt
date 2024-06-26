package com.example.a2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson

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

        //Populate linear layout with data
        val listings = listOf<Listing>(
            Listing("123 Main St", "$200,000", R.drawable.apt1),
            Listing("456 Elm St", "$180,000", R.drawable.apt2),
            Listing("789 Oak St", "$220,000", R.drawable.apt3)
        )
        val linearLayoutHomes = findViewById<LinearLayout>(R.id.llApt)

        for (listing in listings) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_home, linearLayoutHomes, false)

            val imageView = view.findViewById<ImageView>(R.id.ivHome)
            val tvAddress = view.findViewById<TextView>(R.id.tvAddress)
            val tvPrice = view.findViewById<TextView>(R.id.tvPrice)

            imageView.setImageResource(listing.image)
            tvAddress.text = listing.title
            tvPrice.text = listing.price

            linearLayoutHomes.addView(view)
        }

        // Saving selection under shared Preferences
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_APPEND)
        val editor = sharedPreferences.edit()
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnCheckOut = findViewById<Button>(R.id.btnCheckOut)

        btnAdd.setOnClickListener {
            val selectedListings = mutableListOf<Listing>()

            for (i in 0 until linearLayoutHomes.childCount) {
                val view = linearLayoutHomes.getChildAt(i)
                val checkBox = view.findViewById<CheckBox>(R.id.checkBox)

                if (checkBox.isChecked) {
                    val listing = listings[i]
                    selectedListings.add(listing)
                }
            }

            val gson = Gson()
            val listingsJson = gson.toJson(selectedListings)

            editor.putString("selected_listings", listingsJson)
            editor.apply()
        }

        btnCheckOut.setOnClickListener {
            intent = Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
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