package com.example.a2

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmation)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPreferences = getSharedPreferences("CheckOutPref", MODE_PRIVATE)
        val selectedListingJson = sharedPreferences.getString("selected_listing", null)
        //Log.d("ConfirmationActivity", "JSON from SharedPreferences: $selectedListingJson")
        val selectedListing = Gson().fromJson(selectedListingJson, Listing::class.java)
        //Log.d("ConfirmationActivity", "JSON from SharedPreferences: $selectedListing")
        val llOrderDetails = findViewById<LinearLayout>(R.id.llOrderDetails)


        val view = LayoutInflater.from(this)
            .inflate(R.layout.item_home_checkout, llOrderDetails, false)

        val tvAddress = view.findViewById<TextView>(R.id.tvAddress)
        val tvPrice = view.findViewById<TextView>(R.id.tvPrice)
        val imageView = view.findViewById<ImageView>(R.id.ivHome)

        tvAddress.text = selectedListing.title
        tvPrice.text = selectedListing.price
        imageView.setImageResource(selectedListing.image)


        llOrderDetails.addView(view)
    }
}

