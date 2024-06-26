package com.example.a2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson

class CheckoutActivity : AppCompatActivity() {
    private var selectedRadioButton: RadioButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_checkout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val sharedPref = getSharedPreferences("MyPrefs", Context.MODE_APPEND)
        val getSelectedListings = sharedPref.getString("selected_listings", null)
        val selectedListings = if (getSelectedListings != null) {
            Gson().fromJson(getSelectedListings, Array<Listing>::class.java).toList()
        } else {
            emptyList()
        }
        val llCheckOut = findViewById<LinearLayout>(R.id.llCheckOut)

        for (listing in selectedListings) {
            val view = LayoutInflater.from(this).inflate(R.layout.item_home_checkout, llCheckOut, false)

            //val checkBox = view.findViewById<CheckBox>(R.id.checkBox)
            val radioButton = view.findViewById<RadioButton>(R.id.rbCheckout)
            val imageView = view.findViewById<ImageView>(R.id.ivHome)
            val tvAddress = view.findViewById<TextView>(R.id.tvAddress)
            val tvPrice = view.findViewById<TextView>(R.id.tvPrice)

            imageView.setImageResource(listing.image)
            tvAddress.text = listing.title
            tvPrice.text = listing.price

            radioButton.setOnClickListener {
                selectedRadioButton?.isChecked = false
                radioButton.isChecked = true
                selectedRadioButton = radioButton
            }


            llCheckOut.addView(view)
        }
    }
}