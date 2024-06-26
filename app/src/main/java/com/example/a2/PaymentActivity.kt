package com.example.a2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PaymentActivity : AppCompatActivity() {

    private lateinit var etCreditCardNumber: EditText
    private lateinit var etCardholderName: EditText
    private lateinit var etExpiryDate: EditText
    private lateinit var etCvv: EditText
    private lateinit var etAddress: EditText
    private lateinit var btnPlaceOrder: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etCreditCardNumber = findViewById(R.id.et_credit_card_number)
        etCardholderName = findViewById(R.id.et_cardholder_name)
        etExpiryDate = findViewById(R.id.et_expiry_date)
        etCvv = findViewById(R.id.et_cvv)
        etAddress = findViewById(R.id.et_address)
        btnPlaceOrder = findViewById(R.id.btnPlaceOrder)


        btnPlaceOrder.setOnClickListener {
            if (validateInputs()) {
                // Proceed with placing the order
                Toast.makeText(this, "Order Placed Successfully!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ConfirmationActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Validate all inputs
    private fun validateInputs(): Boolean {
        var isValid = true

        // Validate Credit Card Number
        val creditCardNumber = etCreditCardNumber.text.toString()
        if (creditCardNumber.isEmpty() || creditCardNumber.length != 16) {
            etCreditCardNumber.error = "Invalid Credit Card Number"
            isValid = false
        }

        // Validate Cardholder Name
        val cardholderName = etCardholderName.text.toString()
        if (cardholderName.isEmpty()) {
            etCardholderName.error = "Name on the card is required"
            isValid = false
        }

        // Validate Expiry Date
        val expiryDate = etExpiryDate.text.toString()
        if (expiryDate.isEmpty()) {
            etExpiryDate.error = "Expiry Date is required"
            isValid = false
        } else if (!expiryDate.matches(Regex("(0[1-9]|1[0-2])/([0-9]{2})"))) {
            etExpiryDate.error = "Invalid Expiry Date format"
            isValid = false
        }

        // Validate CVV
        val cvv = etCvv.text.toString()
        if (cvv.isEmpty() || cvv.length != 3) {
            etCvv.error = "Invalid CVV"
            isValid = false
        }

        // Validate Address
        val address = etAddress.text.toString()
        if (address.isEmpty()) {
            etAddress.error = "Address is required"
            isValid = false
        }

        return isValid
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